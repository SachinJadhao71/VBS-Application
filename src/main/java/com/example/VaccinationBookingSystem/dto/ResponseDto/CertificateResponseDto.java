package com.example.VaccinationBookingSystem.dto.ResponseDto;


import com.example.VaccinationBookingSystem.Enum.DoseType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CertificateResponseDto {

    Date dose1Date;

    DoseType dose1Type;

    Date dose2Date;

    DoseType dose2Type;

    String message;
}
