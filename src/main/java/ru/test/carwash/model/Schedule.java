package ru.test.carwash.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "schedule")
@Builder
@JsonInclude
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "serviceid")
    private Long serviceId;

    @Column(name = "visittime")
    private Date visitTime;
}