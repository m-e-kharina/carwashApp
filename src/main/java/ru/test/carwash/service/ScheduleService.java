package ru.test.carwash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.carwash.dao.ScheduleDAO;
import ru.test.carwash.dto.ScheduleDTO;
import ru.test.carwash.model.Schedule;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceService serviceService;

    private Schedule buildSchedule(ScheduleDTO scheduleDTO) {
        return Schedule.builder()
                .serviceId(scheduleDTO.getServiceId())
                .userId(scheduleDTO.getUserId())
                .visitTime(scheduleDTO.getVisitTime())
                .build();
    }

    @Transactional
    public Schedule addVisit(ScheduleDTO scheduleDTO) {
        if (scheduleDTO.getServiceId() == null ||scheduleDTO.getUserId() == null || scheduleDTO.getVisitTime() == null) {
            throw new RuntimeException("Некорректные данные!");
        }
        if(!serviceService.existsById(scheduleDTO.getServiceId())) {
            throw new RuntimeException("Услуги не существует");
        }
        if(!userService.existsById(scheduleDTO.getUserId())) {
            throw new RuntimeException("Пользователя не существует");
        }
        Date currentDateTime = new Date();
        if (scheduleDTO.getVisitTime().before(currentDateTime)) {
            throw new RuntimeException("Нельзя записаться на прошедшее время");
        }
        if(scheduleDAO.existsByVisitTime(scheduleDTO.getVisitTime())) {
            throw new RuntimeException("Это время уже занято");
        }
        return scheduleDAO.save(buildSchedule(scheduleDTO));
    }

    @Transactional
    public List<Schedule> getSchedule(Long userId) {
        if (userService.checkAdminRights(userId)) {
            return scheduleDAO.findAll();
        }
        else {
            return scheduleDAO.findAllByUserId(userId);
        }
    }

    @Transactional
    public String getTimeBeforeService(Long userId) {
        try {
            Schedule schedule = scheduleDAO.findAllByUserId(userId)
                    .stream()
                    .sorted((s1, s2) -> (int) (s1.getVisitTime().getTime() - s2.getVisitTime().getTime()))
                    .findFirst()
                    .get();

            Date currentDate = new Date();
            LocalDateTime currentLDT = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
            LocalDateTime ldt = LocalDateTime.ofInstant(schedule.getVisitTime().toInstant(), ZoneId.systemDefault());
            long days = currentLDT.until(ldt, ChronoUnit.DAYS);
            currentLDT = currentLDT.plusDays(days);

            long hours = currentLDT.until(ldt, ChronoUnit.HOURS);
            currentLDT = currentLDT.plusHours(hours);

            long minutes = currentLDT.until(ldt, ChronoUnit.MINUTES);

            return days + " день(дней) " + hours + " часа(ов) " + minutes + " минут(ы)";
        }
        catch (NoSuchElementException ex) {
            throw new RuntimeException("У пользователя нет записей");
        }
    }
}