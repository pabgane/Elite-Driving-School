package edu.ijse.elite_driving_schoolorm.dao.custom;

import edu.ijse.elite_driving_schoolorm.dao.SuperDAO;

public interface QueryDAO extends SuperDAO {
    public int getStudentCountForLesson(String lessonId);
}
