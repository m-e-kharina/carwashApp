package ru.test.carwash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.test.carwash.dao.ServiceDAO;

import javax.transaction.Transactional;

@Service
public class ServiceService {

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private UserService userService;

    private ru.test.carwash.model.Service buildService(String serviceName) {
        return ru.test.carwash.model.Service.builder()
                .name(serviceName)
                .build();
    }

    @Transactional
    public void addService(String serviceName, Long userId) {
        if(userService.checkAdminRights(userId)) {
            if(!serviceDAO.existsByName(serviceName)) {
                serviceDAO.save(buildService(serviceName));
            }
            else {
                throw new RuntimeException("Услуга уже существует");
            }
        }
        else {
            throw new RuntimeException("Нет прав на операцию");
        }
    }

    @Transactional
    public boolean existsById(Long id) {
        return serviceDAO.existsById(id);
    }

    @Transactional
    public void deleteService(String serviceName, Long userId) {
        if(userService.checkAdminRights(userId)) {
            if(serviceDAO.existsByName(serviceName)) {
                serviceDAO.deleteByName(serviceName);
            }
            else {
                throw new RuntimeException("Услуги не существует");
            }
        }
        else {
            throw new RuntimeException("Нет прав на операцию");
        }
    }
}
