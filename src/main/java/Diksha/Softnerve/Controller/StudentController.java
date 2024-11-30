package Diksha.Softnerve.Controller;

import Diksha.Softnerve.DTO.StudentDTO;
import Diksha.Softnerve.Entity.Student;
import Diksha.Softnerve.Service.ServiceImpl.MongoDbAutoSequenceService;
import Diksha.Softnerve.Service.ServiceImpl.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private MongoDbAutoSequenceService sequenceService;

    @GetMapping("/welcome")
    public String welcome(Model model){
        model.addAttribute("message","Welcome this endpoint is freeeee....");
        return "welcome";
    }

    @PostMapping("/create")
    public StudentDTO createStudent(@RequestBody @Valid StudentDTO studentDTO) {
        studentDTO.setId(sequenceService.getsequencenumber(Student.SEQUENCE_NAME));

        return studentService.createStudent(studentDTO);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentByid(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody @Valid StudentDTO studentDTO, @PathVariable Long id) {
        return ResponseEntity.ok(studentService.upadateStudent(studentDTO, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully with ID: " + id);
    }

    @GetMapping("/students/{page}/{size}")
    public ResponseEntity<Page<StudentDTO>> getListOfStudents(@PathVariable int page, @PathVariable int size) {
        return ResponseEntity.ok(studentService.ListOfStudent(page, size));
    }
}
