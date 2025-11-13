package edu.ijse.elite_driving_schoolorm.bo.custom.impl;

import edu.ijse.elite_driving_schoolorm.bo.custom.StudentCourseDetailBO;
import edu.ijse.elite_driving_schoolorm.bo.exception.DuplicateException;
import edu.ijse.elite_driving_schoolorm.bo.util.EntityDTOConverter;
import edu.ijse.elite_driving_schoolorm.dao.DAOFactory;
import edu.ijse.elite_driving_schoolorm.dao.DAOTypes;
import edu.ijse.elite_driving_schoolorm.dao.custom.CourseDAO;
import edu.ijse.elite_driving_schoolorm.dao.custom.StudentCourseDetailDAO;
import edu.ijse.elite_driving_schoolorm.dao.custom.StudentDAO;
import edu.ijse.elite_driving_schoolorm.dto.StudentCourseDetailsDTO;
import edu.ijse.elite_driving_schoolorm.entity.Course;
import edu.ijse.elite_driving_schoolorm.entity.StudentCourseDetails;
import edu.ijse.elite_driving_schoolorm.entity.Students;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentCourseDetailBOImpl implements StudentCourseDetailBO {

    private final StudentCourseDetailDAO studentCourseDetailDAO = (StudentCourseDetailDAO) DAOFactory.getInstance().getDAO(DAOTypes.STUDENT_COURSE_DETAILS);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOTypes.STUDENTS);
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOTypes.COURSE);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<StudentCourseDetailsDTO> getAllStudentCourseDetails() throws Exception {
        List<StudentCourseDetails> studentCourseDetails = studentCourseDetailDAO.getAll();
        List<StudentCourseDetailsDTO> studentCourseDetailsDTOs = new ArrayList<>();
        for (StudentCourseDetails studentCourseDetail : studentCourseDetails) {
            studentCourseDetailsDTOs.add(converter.getStudentCourseDetailsDTO(studentCourseDetail));
        }
        return studentCourseDetailsDTOs;
    }

    @Override
    public String getLastStudentCourseDetailId() throws Exception {
        return studentCourseDetailDAO.getLastId();
    }

    @Override
    public boolean saveStudentCourseDetails(StudentCourseDetailsDTO t) throws Exception {
        Optional<Students> studentExists = studentDAO.findById(t.getStudentId());
        Optional<Course> courseExists = courseDAO.findById(t.getCourseId());
        Optional<StudentCourseDetails> studentCourseDetailsExists = studentCourseDetailDAO.findById(t.getStudentCourseId());

        if (studentCourseDetailsExists.isPresent()) {
            throw new DuplicateException("Student Course Details already exists");
        }
        if (studentExists.isPresent() &&  courseExists.isPresent()) {
            return studentCourseDetailDAO.save(converter.getStudentCourseDetailsEntity(t));
        }
        throw new Exception("Student or Course not found");
    }

    @Override
    public boolean updateStudentCourseDetails(StudentCourseDetailsDTO t) throws Exception {
        Optional<StudentCourseDetails>  studentCourseDetailsExists = studentCourseDetailDAO.findById(t.getStudentCourseId());
        if (studentCourseDetailsExists.isEmpty()) {
            throw new Exception("Student Course not found");
        }
        return studentCourseDetailDAO.update(converter.getStudentCourseDetailsEntity(t));
    }

    @Override
    public boolean deleteStudentCourseDetails(String id) throws Exception {
        Optional<StudentCourseDetails>  studentCourseDetailsExists = studentCourseDetailDAO.findById(id);
        if (studentCourseDetailsExists.isEmpty()) {
            throw new Exception("Student Course not found");
        }
        return studentCourseDetailDAO.delete(id);
    }

    @Override
    public List<String> getAllStudentCourseDetailIds() throws Exception {
        return studentCourseDetailDAO.getAllIds();
    }

    @Override
    public Optional<StudentCourseDetailsDTO> findByStudentCourseDetailId(String id) throws Exception {
        Optional<StudentCourseDetails> details = studentCourseDetailDAO.findById(id);
        if (details.isPresent()) {
            return Optional.of(converter.getStudentCourseDetailsDTO(details.get()));
        } else {
            return Optional.empty();
        }
    }
}
