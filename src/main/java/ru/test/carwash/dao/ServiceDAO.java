package ru.test.carwash.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.carwash.model.Service;

@Repository
public interface ServiceDAO extends JpaRepository<Service, Long> {

    boolean existsByName(String name);
    Service findByName(String serviceName);
    int deleteByName(String serviceName);
}
