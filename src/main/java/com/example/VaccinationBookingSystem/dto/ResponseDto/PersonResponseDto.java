package com.example.VaccinationBookingSystem.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PersonResponseDto {

    String name;

    String emailId;

    int age;
}
