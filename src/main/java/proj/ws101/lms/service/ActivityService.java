package proj.ws101.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj.ws101.lms.entity.Activity;
import proj.ws101.lms.repository.ActivityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    public final ActivityRepository activityRepository;

    public ActivityService(@Autowired ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }


    // Method to get assignments by course ID
    public List<Activity> getAssignmentsByCourseId(Long courseId) {
        return activityRepository.findAssignmentsByCourseId(courseId);
    }

    // Method to get assignments by course ID
    public List<Activity> getQuizzesByCourseId(Long courseId) {
        return activityRepository.findQuizzesByCourseId(courseId);
    }

    public Optional<Activity> getActivityById(Long id){
        return activityRepository.findById(id);
    }

//    public List<Activity> getAllActivities(){
//        return activityRepository.findAll();
//    }

    public List<Activity> getAllAssignments(){
        return activityRepository.findByActivityType("assignment");
    }

    public List<Activity> getAllQuizzes(){
        return activityRepository.findByActivityType("quiz");
    }

    // Get all activities and filter for assignments
    public List<Activity> getAllActivities() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream()
                .filter(activity -> "Assignment".equals(activity.getActivityType())) // Filter by "assignment"
                .collect(Collectors.toList());
    }

    public void addActivity(Activity activity){
        activityRepository.save(activity);
    }

    public void deleteActivityById(Long id){
        activityRepository.deleteById(id);
    }
}
