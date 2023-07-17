package com.example.VaccinationBookingSystem.dto.RequestDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookAppointmentRequestDto {

    int personId;

    int doctorId;
}
