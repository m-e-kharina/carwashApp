package ru.test.carwash.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.test.carwash.dto.ScheduleDTO;
import ru.test.carwash.model.Schedule;
import ru.test.carwash.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping(produces="application/json")
@Tag(name="ScheduleController", description="Управление записью на автомойку")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addVisit")
    @Operation(
            summary = "Добавление новой записи",
            description = "Позволяет пользователю добавить запись на какую-либо услугу"
    )
    public Schedule addVisit(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.addVisit(scheduleDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/allVisits")
    @Operation(
            summary = "Просмотр записей",
            description = "Позволяет пользователю просмотреть свои записи, а администратору - увидеть все записи"
    )
    public List<Schedule> getSchedule(@RequestParam @Parameter(description = "ИД пользователя") Long userId) {
        return scheduleService.getSchedule(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getTimeBeforeVisit")
    @Operation(
            summary = "Осталось времени",
            description = "Позволяет пользователю увидеть, сколько времени осталось до его ближайшей записи"
    )
    public String getTimeBeforeService(@RequestParam @Parameter(description = "ИД пользователя") Long userId) {
        return scheduleService.getTimeBeforeService(userId);
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException e) {
        return e.getMessage();
    }
}
