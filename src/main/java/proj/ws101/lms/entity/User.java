package proj.ws101.lms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String studentNumber;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private String suffix;
    private String role;
    private String userProfile;
    private Boolean isActive;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate birthday;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Teacher teacher;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Grade> grades;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Enrollment> enrollments;
}
