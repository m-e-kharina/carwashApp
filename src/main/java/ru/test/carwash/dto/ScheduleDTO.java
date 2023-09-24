package ru.test.carwash.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class ScheduleDTO {
    private Long id;
    private Long serviceId;
    private Long userId;
    private Date visitTime;
}
