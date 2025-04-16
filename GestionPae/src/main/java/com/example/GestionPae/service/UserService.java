package com.example.GestionPae.service;


import com.example.GestionPae.enums.Rol;
import com.example.GestionPae.model.User;
import com.example.GestionPae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    //List Rol USER_SCHOOL
    public List<User> listUser() {
        return userRepository.findByRol(Rol.USER_SCHOOL);
    }

    //search by name school
    public List<User> searchUser(String userName) {
        return userRepository.findByNameContainingIgnoreCase(userName);
    }

    //Create
    public User createUser(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return userRepository.save(user);
    }

    //Delete
    public void deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

}
