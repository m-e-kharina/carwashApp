package ru.test.carwash.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.test.carwash.service.ServiceService;

@RestController
@RequestMapping(produces="application/json")
@Tag(name="ServiceController", description="Управление услугами")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping("/addNewService")
    @Operation(
            summary = "Добавление новой услуги",
            description = "Позволяет администратору добавить услугу"
    )
    public void addNewService(@RequestParam @Parameter(description = "Наименование услуги") String serviceName,
                              @RequestParam @Parameter(description = "ИД пользователя") Long userId) {
        serviceService.addService(serviceName, userId);
    }

    @DeleteMapping("/deleteService")
    @Operation(
            summary = "Удаление услуги",
            description = "Позволяет администратору удалить услугу"
    )
    public void deleteService(@RequestParam @Parameter(description = "Наименование услуги") String serviceName,
                              @RequestParam @Parameter(description = "ИД пользователя") Long userId) {
        serviceService.deleteService(serviceName, userId);
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException e) {
        return e.getMessage();
    }
}
