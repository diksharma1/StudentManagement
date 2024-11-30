package Diksha.Softnerve.Service.ServiceImpl;

import Diksha.Softnerve.Entity.Student;
import Diksha.Softnerve.ExceptionHandling.ResourceNotFoundException;
import Diksha.Softnerve.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws ResourceNotFoundException {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with email: " + email));
        return new org.springframework.security.core.userdetails.User(
                student.getEmail(),
                student.getPassword(),
                new ArrayList<>());

    }
}
