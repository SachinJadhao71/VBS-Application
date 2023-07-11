package com.example.VaccinationBookingSystem.Model;


import com.example.VaccinationBookingSystem.Enum.DoseType;
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
public class Dose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String doseId;

    @Enumerated(value = EnumType.STRING)
    DoseType doseType;

    @CreationTimestamp
    Date vaccinationDate;

//    from dose to person is many to one relationship
//    first one is current class and second is connecting class

    @ManyToOne
    @JoinColumn
    Person person;
}