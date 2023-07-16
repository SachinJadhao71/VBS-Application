package com.example.VaccinationBookingSystem.dto.RequestDto;

import com.example.VaccinationBookingSystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GetAllMalesRequestDto {

    int age;

    Gender gender;
}
