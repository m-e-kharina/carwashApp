package ru.test.carwash.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.carwash.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
}
