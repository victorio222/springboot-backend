package proj.ws101.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import proj.ws101.lms.entity.Course;
import proj.ws101.lms.entity.Teacher;
import proj.ws101.lms.service.TeacherService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public Optional<Teacher> getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }

    @PostMapping("/add")
    public void addTeacher(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }



    @GetMapping("/{id}/courses")
    public List<Course> getTeacherSubjectHandled(@PathVariable Long id){
        Optional<Teacher> teacherOpt = getTeacherById(id);
        if (teacherOpt.isPresent()){
            Teacher teacher = teacherOpt.get();
            return teacher.getCourses();
        } else {
            return List.of();
        }
    }
}
