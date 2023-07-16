package com.example.VaccinationBookingSystem.dto.RequestDto;


import com.example.VaccinationBookingSystem.Enum.DoseType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookDose2RequestDto {

    int personId;

    DoseType doseType;
}
