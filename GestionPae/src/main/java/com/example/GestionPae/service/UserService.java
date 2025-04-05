package com.example.GestionPae.service;


import com.example.GestionPae.model.User;
import com.example.GestionPae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Listar Usuario
    public List<User> listUser() {
        return userRepository.findAll();
    }

    //Crear Usuario
    public User createUser(User user) {
        return userRepository.save(user);
    }

    //Eliminar usuario
    public void deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }



}
