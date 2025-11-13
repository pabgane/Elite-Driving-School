package edu.ijse.elite_driving_schoolorm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentsDTO {
    private String paymentId;
    private Date paymentDate;
    private double amount;
    private String paymentMethod;
    private String status;
    private String studentId;
}
