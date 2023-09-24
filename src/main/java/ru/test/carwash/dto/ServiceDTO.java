package ru.test.carwash.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ServiceDTO {
    private Long id;
    private String name;
}
