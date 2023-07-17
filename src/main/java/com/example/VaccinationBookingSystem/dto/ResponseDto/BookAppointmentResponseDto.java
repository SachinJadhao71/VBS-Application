package com.example.VaccinationBookingSystem.dto.ResponseDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookAppointmentResponseDto {

    String personName;

    String doctorName;

    String appointmentId;

    Date appointmentDate;

    CenterResponseDto centerResponseDto;
}
