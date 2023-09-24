package ru.test.carwash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.carwash.dao.UserDAO;
import ru.test.carwash.dto.UserDTO;
import ru.test.carwash.model.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    private User buildUser(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .role(userDTO.getRole())
                .build();
    }

    @Transactional
    public User registerUser(UserDTO userDTO) {
        if(userDTO.getName() == null) {
            throw new RuntimeException("Введите имя!");
        }
        if(userDTO.getRole() == null) {
            throw new RuntimeException("Не задана роль");
        }
        return userDAO.save(buildUser(userDTO));
    }

    @Transactional
    public boolean checkAdminRights(Long userId) {
        return userDAO.findById(userId).get().getRole().equals("admin");
    }

    @Transactional
    public boolean existsById(Long id) {
        return userDAO.existsById(id);
    }

    @Transactional
    public List<User> findAllUsers() {
        return userDAO.findAll();
    }
}
