package proj.ws101.lms.controller;

import jdk.jfr.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proj.ws101.lms.entity.*;
import proj.ws101.lms.repository.CourseRepository;
import proj.ws101.lms.repository.EnrollmentRepository;
import proj.ws101.lms.service.CourseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    public final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourse();
    }

    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @GetMapping("/{courseId}/people")
    public List<Enrollment> getEnrolledPeople(@PathVariable Long courseId) {
        // Assuming Enrollment entity has a reference to Student and Course
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        return enrollmentRepository.findByCourse(course); // Query to get enrollments for the course
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestBody Course course){
        try {
            courseService.addCourse(course);
            return ResponseEntity.ok("Course added successfully");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Invalid data: " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while adding the course.");
        }
    }

    @GetMapping("/{id}/announcements")
    public List<Announcement> getAnnouncementsByCourseId(@PathVariable Long id){
        Optional<Course> courseOptional = courseService.getCourseById(id);

        if (courseOptional.isPresent()){
            Course course = courseOptional.get();

            return course.getAnnouncements();
        } else {
            return List.of();
        }
    }

    @GetMapping("/{id}/assignments")
    public List<Activity> getAssignmentById(@PathVariable Long id){
        Optional<Course> courseOptional = courseService.getCourseById(id);

        if (courseOptional.isPresent()){
            Course course = courseOptional.get();

            return course.getActivities();
        } else {
            return List.of();
        }
    }

    @DeleteMapping("/delete")
    public void deleteCourseById(@RequestParam Long id){
        courseService.deleteCourseById(id);
    }

    @GetMapping("/{id}/modules")
    public List<LearningMaterial> getLearningMaterialsByCourseId(@PathVariable Long id ){
        Optional<Course> courseOptional = courseService.getCourseById(id);

        if (courseOptional.isPresent()){
            Course course = courseOptional.get();

            return course.getLearningMaterials();
        } else {
            return List.of();
        }
    }


    @GetMapping("/{id}/published")
    public List<LearningMaterial> publishedModules(@PathVariable Long id){
        Optional<Course> courseOptional = courseService.getCourseById(id);

        if (courseOptional.isPresent()){
            Course course = courseOptional.get();

            List<LearningMaterial> learningMaterials = course.getLearningMaterials();

            return learningMaterials.stream().filter(LearningMaterial::isStatus)
                    .toList();
        } else {
            return List.of();
        }
    }


    @GetMapping("/{id}/activities")
    public ResponseEntity<?> getAllActivitiesByCourseId(@PathVariable Long id) {
        Optional<Course> courseOptional = courseService.getCourseById(id);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            Map<String, Object> activities = new HashMap<>();
            activities.put("announcements", course.getAnnouncements());
            activities.put("assignments", course.getActivities());
            activities.put("learningMaterials", course.getLearningMaterials());
            return ResponseEntity.ok(activities);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
    }

}
