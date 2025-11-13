package edu.ijse.elite_driving_schoolorm.bo.custom.impl;

import edu.ijse.elite_driving_schoolorm.bo.custom.InstructorBO;
import edu.ijse.elite_driving_schoolorm.bo.util.EntityDTOConverter;
import edu.ijse.elite_driving_schoolorm.dao.DAOFactory;
import edu.ijse.elite_driving_schoolorm.dao.DAOTypes;
import edu.ijse.elite_driving_schoolorm.dao.custom.InstructorDAO;
import edu.ijse.elite_driving_schoolorm.dto.InstructorDTO;
import edu.ijse.elite_driving_schoolorm.entity.Instructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InstructorBOImpl implements InstructorBO {

    private final InstructorDAO instructorDAO = DAOFactory.getInstance().getDAO(DAOTypes.INSTRUCTORS);
    private final EntityDTOConverter convertor = new EntityDTOConverter();


    @Override
    public List<InstructorDTO> getAllInstructors() throws Exception {
        List<Instructor> instructors = instructorDAO.getAll();
        List<InstructorDTO> instructorDTOs = new ArrayList<>();
        for (Instructor instructor : instructors) {
            instructorDTOs.add(convertor.getInstructorsDTO(instructor));
        }
        return instructorDTOs;
    }

    @Override
    public String getLastInstructorId() throws Exception {
        return instructorDAO.getLastId();
    }

    @Override
    public boolean saveInstructors(InstructorDTO t) throws Exception {
        Optional<Instructor> instructors = instructorDAO.findById(t.getInstructorId());
        if (instructors.isPresent()) {
            throw new Exception("Instructor already exists");
        }
        return instructorDAO.save(convertor.getInstructorsEntity(t));
    }

    @Override
    public boolean updateInstructors(InstructorDTO t) throws Exception {
        Optional<Instructor> instructors = instructorDAO.findById(t.getInstructorId());
        if (instructors.isEmpty()) {
            throw new Exception("Instructor Not Found");
        }
        return instructorDAO.update(convertor.getInstructorsEntity(t));
    }

    @Override
    public boolean deleteInstructors(String id) throws Exception {
        Optional<Instructor> instructors = instructorDAO.findById(id);
        if (instructors.isEmpty()) {
            throw new Exception("Instructor not Found");
        }
        return instructorDAO.delete(id);
    }

    @Override
    public List<String> getAllInstructorIds() throws Exception {
        return instructorDAO.getAllIds();
    }

    @Override
    public Optional<InstructorDTO> findByInstructorId(String id) throws Exception {
        Optional<Instructor> instructors = instructorDAO.findById(id);
        if (instructors.isPresent()) {
            return Optional.of(convertor.getInstructorsDTO(instructors.get()));
        }
        return Optional.empty();
    }

    @Override
    public String generateNewInstructorsId() {
        return instructorDAO.generateNewId();
    }
}
