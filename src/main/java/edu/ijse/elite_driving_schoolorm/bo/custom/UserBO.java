package edu.ijse.elite_driving_schoolorm.bo.custom;

import edu.ijse.elite_driving_schoolorm.bo.SuperBO;
import edu.ijse.elite_driving_schoolorm.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserBO extends SuperBO {
    List<UserDTO> getAllUsers() throws Exception;

    String getLastUserId() throws Exception;

    boolean saveUsers(UserDTO t) throws Exception;

    boolean updateUsers(UserDTO t) throws Exception;

    boolean deleteUsers(String id) throws Exception;

    List<String> getAllUserIds() throws Exception;

    Optional<UserDTO> findByUserId(String id) throws Exception;

    String generateNextUserId();

    public UserDTO getUserByEmail(String email) ;
}
