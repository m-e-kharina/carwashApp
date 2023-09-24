package ru.test.carwash.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.carwash.model.Schedule;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleDAO extends JpaRepository<Schedule, Long> {

    boolean existsByVisitTime(Date visitTime);
    List<Schedule> findAllByUserId(Long userId);
}
