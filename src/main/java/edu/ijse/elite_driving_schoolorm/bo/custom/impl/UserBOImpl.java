package edu.ijse.elite_driving_schoolorm.bo.custom.impl;

import edu.ijse.elite_driving_schoolorm.bo.custom.UserBO;
import edu.ijse.elite_driving_schoolorm.bo.exception.DuplicateException;
import edu.ijse.elite_driving_schoolorm.bo.exception.NotFoundException;
import edu.ijse.elite_driving_schoolorm.bo.util.EntityDTOConverter;
import edu.ijse.elite_driving_schoolorm.dao.DAOFactory;
import edu.ijse.elite_driving_schoolorm.dao.DAOTypes;
import edu.ijse.elite_driving_schoolorm.dao.custom.UserDAO;
import edu.ijse.elite_driving_schoolorm.dto.UserDTO;
import edu.ijse.elite_driving_schoolorm.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBOImpl implements UserBO {

    private  final UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOTypes.USER);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        List<User> userList = userDAO.getAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(converter.getUserDTO(user));
        }
        return userDTOList;
    }

    @Override
    public String getLastUserId() throws Exception {
        return userDAO.getLastId();
    }

    @Override
    public boolean saveUsers(UserDTO t) throws Exception {
        Optional<User> user = userDAO.findById(t.getUserId());
        if (user.isPresent()) {
            throw new DuplicateException("User already exists");
        }
        return userDAO.save(converter.getUserEntity(t));

    }

    @Override
    public boolean updateUsers(UserDTO t) throws Exception {
        Optional<User> user = userDAO.findById(t.getUserId());
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return userDAO.update(converter.getUserEntity(t));
    }

    @Override
    public boolean deleteUsers(String id) throws Exception {
        Optional<User> user = userDAO.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return userDAO.delete(id);
    }

    @Override
    public List<String> getAllUserIds() throws Exception {
        return userDAO.getAllIds();
    }

    @Override
    public Optional<UserDTO> findByUserId(String id) throws Exception {
        Optional<User> user = userDAO.findById(id);
        if (user.isPresent()) {
            return Optional.of(converter.getUserDTO(user.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String generateNextUserId() {
        return userDAO.generateNewId();
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userDAO.getUserByEmail(email);

        if (user != null) {
            return converter.getUserDTO(user);
        } else {
            System.out.println("No user found with that email: " + email);
            return null;
        }
    }
}
