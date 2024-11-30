package Diksha.Softnerve.Mapper;

import Diksha.Softnerve.DTO.StudentDTO;
import Diksha.Softnerve.Entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentMapper {
    @Autowired
    private ModelMapper modelMapper;

    public StudentDTO StudenttoStudentDTO(Student student){
        return modelMapper.map(student,StudentDTO.class);
    }
    public Student StudentDTOtoStudent(StudentDTO studentDTO){
        return modelMapper.map(studentDTO,Student.class);
    }


}
