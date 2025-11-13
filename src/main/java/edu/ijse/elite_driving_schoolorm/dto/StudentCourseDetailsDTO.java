package edu.ijse.elite_driving_schoolorm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StudentCourseDetailsDTO {
    private String studentCourseId;
    private String studentId;
    private String courseId;
    private Date enrollmentDate;
    private String status;
    private String grade;
}
