package com.example.VaccinationBookingSystem.Model;


import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String appointmentId;

    @CreationTimestamp
    Date appointmentDate;

    @ManyToOne
    @JoinColumn
    Person person;


    @ManyToOne
    @JoinColumn
    Doctor doctor;
}
