package com.example.VaccinationBookingSystem.dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DoctorRequestDto {

    int age;

    String oldEmail;

    String newEmail;
}
