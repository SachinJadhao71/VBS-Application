package com.example.VaccinationBookingSystem.transformer;

import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.dto.ResponseDto.PersonResponseDto;

public class PersonTransformer {

    public static PersonResponseDto PersonToPersonResponseDto(Person person){

        return PersonResponseDto.builder()
                .name(person.getName())
                .age(person.getAge())
                .emailId(person.getEmailId())
                .build();
    }
}
