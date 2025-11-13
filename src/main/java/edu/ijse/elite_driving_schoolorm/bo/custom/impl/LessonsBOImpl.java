package edu.ijse.elite_driving_schoolorm.bo.custom.impl;

import edu.ijse.elite_driving_schoolorm.bo.custom.LessonsBO;
import edu.ijse.elite_driving_schoolorm.bo.exception.DuplicateException;
import edu.ijse.elite_driving_schoolorm.bo.exception.NotFoundException;
import edu.ijse.elite_driving_schoolorm.bo.util.EntityDTOConverter;
import edu.ijse.elite_driving_schoolorm.dao.DAOFactory;
import edu.ijse.elite_driving_schoolorm.dao.DAOTypes;
import edu.ijse.elite_driving_schoolorm.dao.custom.CourseDAO;
import edu.ijse.elite_driving_schoolorm.dao.custom.InstructorDAO;
import edu.ijse.elite_driving_schoolorm.dao.custom.LessonsDAO;
import edu.ijse.elite_driving_schoolorm.dao.custom.QueryDAO;
import edu.ijse.elite_driving_schoolorm.dto.LessonsDTO;
import edu.ijse.elite_driving_schoolorm.entity.Lessons;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LessonsBOImpl implements LessonsBO {

    private final LessonsDAO lessonsDAO = (LessonsDAO) DAOFactory.getInstance().getDAO(DAOTypes.LESSONS);
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOTypes.COURSE);
    private final InstructorDAO instructorDAO =  (InstructorDAO) DAOFactory.getInstance().getDAO(DAOTypes.INSTRUCTORS);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOTypes.QUERY);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<LessonsDTO> getAllLessons() throws Exception {
        List<Lessons> lessons = lessonsDAO.getAll();
        List<LessonsDTO> lessonsDTOS = new ArrayList<>();
        for (Lessons lesson : lessons) {
            lessonsDTOS.add(converter.getLessonsDTO(lesson));
        }
        return lessonsDTOS;
    }

    @Override
    public String getLastLessonId() throws Exception {
        return lessonsDAO.getLastId();
    }

    @Override
    public boolean saveLessons(LessonsDTO t) throws Exception {

        boolean courseExists = courseDAO.findById(t.getCourseId()).isPresent();
        boolean instructorExists = instructorDAO.findById(t.getInstructorId()).isPresent();

        if (courseExists && instructorExists) {
            return lessonsDAO.save(converter.getLessonsEntity(t));
        }
        throw new Exception("Lessons not saved");
    }

    @Override
    public boolean updateLessons(LessonsDTO t) throws Exception {
        Optional<Lessons> lessons = lessonsDAO.findById(t.getLessonId());
        if (lessons.isEmpty()) {
            throw new DuplicateException("Lessons Not Found");
        }
        return lessonsDAO.update(converter.getLessonsEntity(t));
    }

    @Override
    public boolean deleteLessons(String id) throws Exception {
        Optional<Lessons> lesson = lessonsDAO.findById(id);
        if (lesson.isEmpty()) {
            throw new NotFoundException("Lesson not found");
        }

        int studentsEnrolled = queryDAO.getStudentCountForLesson(id);
        if (studentsEnrolled > 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Students are enrolled in this lesson. Are you sure you want to delete?",
                    ButtonType.YES,
                    ButtonType.NO);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);

            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
            if (result != ButtonType.YES) {
                return false; // User cancelled deletion
            }
        }

        return lessonsDAO.delete(id);
    }

    @Override
    public List<String> getAllLessonIds() throws Exception {
        return lessonsDAO.getAllIds();
    }

    @Override
    public Optional<LessonsDTO> findByLessonId(String id) throws Exception {
        Optional<Lessons> lessons = lessonsDAO.findById(id);
        if (lessons.isPresent()) {
            return Optional.of(converter.getLessonsDTO(lessons.get()));
        }
        return Optional.empty();
    }

    @Override
    public String generateNewLessonId() {
        return lessonsDAO.generateNewId();
    }
}
