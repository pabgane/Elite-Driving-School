package edu.ijse.elite_driving_schoolorm.dao.custom;

import edu.ijse.elite_driving_schoolorm.dao.CrudDAO;
import edu.ijse.elite_driving_schoolorm.entity.User;

public interface UserDAO extends CrudDAO<User> {
    public User getUserByEmail(String email);
}
