package com.example.VaccinationBookingSystem.dto.ResponseDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CertificateResponseDto {

    String personName;

//    Date Dose1Date;
//
//    Date Dose2Date;

    String message;
}
