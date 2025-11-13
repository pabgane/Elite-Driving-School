package edu.ijse.elite_driving_schoolorm.bo.custom;

import edu.ijse.elite_driving_schoolorm.dto.PaymentsDTO;

import java.util.List;
import java.util.Optional;

public interface PaymentsBO {
    List<PaymentsDTO> getAllPayments() throws Exception;

    String getLastPaymentId() throws Exception;

    boolean savePayments(PaymentsDTO t) throws Exception;

    boolean updatePayments(PaymentsDTO t) throws Exception;

    boolean deletePayments(String id) throws Exception;

    List<String> getAllPaymentIds() throws Exception;

    Optional<PaymentsDTO> findByPaymentId(String id) throws Exception;

    String generateNewPaymentId();
}
