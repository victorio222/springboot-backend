//package proj.ws101.lms.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import jakarta.annotation.Nullable;
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.cglib.core.Local;
//
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Data
//@Table(name = "activities")
//public class Activity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "activity_id")
//    private Long activityId;
//
//    @ManyToOne
//    @JoinColumn(name = "course_id", nullable = false)
//    @JsonBackReference
//    private Course course;
//
//    @Getter
//    @Setter
//    @Column(nullable = false)
//    private String activityType;
//    private String activityDescription;
//    private LocalDate dueDate;
//
//    @OneToMany(mappedBy = "activity")
//    private List<Grade> grades;
//}


package proj.ws101.lms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long activityId;

//    @ManyToOne
//    @JoinColumn(name = "course_id", nullable = false)
//    @JsonBackReference
//    private Course course;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "courseId", nullable = false)
    @JsonBackReference
    private Course course;  // Ensure this is mapped correctly in the database.

    @Column(nullable = false)
    private String activityType;  // "quiz" or "assignment"
    private String activityName;
    private String activityDescription;  // Description of the activity
    private LocalDate dueDate;          // Due date for the activity

    private Integer points;            // Points for the activity

    @OneToMany(mappedBy = "activity")
    private List<Grade> grades;        // Grades for the activity

}
