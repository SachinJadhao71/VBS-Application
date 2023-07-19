package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Enum.DoseType;
import com.example.VaccinationBookingSystem.Exceptions.Dose1NotCompletedException;
import com.example.VaccinationBookingSystem.Exceptions.DoseAlreadyTakenException;
import com.example.VaccinationBookingSystem.Exceptions.PersonNotFoundException;
import com.example.VaccinationBookingSystem.Model.Dose;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Repository.DoseRepository;
import com.example.VaccinationBookingSystem.Repository.PersonRepository;
import com.example.VaccinationBookingSystem.dto.RequestDto.BookDose1RequestDto;
import com.example.VaccinationBookingSystem.dto.RequestDto.BookDose2RequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.BookDose1ResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.BookDose2ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {

    @Autowired
    DoseRepository doseRepository;

    @Autowired
    PersonRepository personRepository;

//    public Dose getDose1(int personId, DoseType doseType) {
//
////        check that person exists or not
//        Optional<Person> optionalPerson = personRepository.findById(personId);
//
//        if(!optionalPerson.isPresent()){
//            throw new PersonNotFoundException("Invalid personId");
//        }
//
//        Person person = optionalPerson.get();
//
////        check dose taken or not
//        if(person.isDose1Taken()){
//            throw new DoseAlreadyTakenException("Dose is already taken");
//        }
//
////        we have to set all dose value
//        Dose dose = new Dose();
//
//        dose.setDoseId(String.valueOf(UUID.randomUUID()));
//        dose.setDoseType(doseType);
//        dose.setPerson(person);
//
//
//        person.setDose1Taken(true);
//        personRepository.save(person);
//
//        Dose doseTaken = doseRepository.save(dose);
//
//        return doseTaken;
//    }


    public BookDose1ResponseDto getDose1(BookDose1RequestDto bookDose1RequestDto) {

//        check that person exists or not
        Optional<Person> optionalPerson = personRepository.findById(bookDose1RequestDto.getPersonId());

        if(!optionalPerson.isPresent()){
            throw new PersonNotFoundException("Invalid personId");
        }

        Person person = optionalPerson.get();

//        check dose taken or not
        if(person.isDose1Taken()){
            throw new DoseAlreadyTakenException("Dose is already taken");
        }

//        we have to set all dose value
        Dose dose = new Dose();

        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDose1RequestDto.getDoseType());
        dose.setPerson(person);


        person.setDose1Taken(true);
        person.getDoseTaken().add(dose);

        Person savedPerson = personRepository.save(person);


        Dose savedDose =  savedPerson.getDoseTaken().get(0);

//        convert entity to response dto

        BookDose1ResponseDto bookDose1ResponseDto = new BookDose1ResponseDto();

        bookDose1ResponseDto.setName(person.getName());
        bookDose1ResponseDto.setMessage("Your Dose 1 of " + savedDose.getDoseType() + " successfully completed");

        return bookDose1ResponseDto;
    }

    public BookDose2ResponseDto getDose2(BookDose2RequestDto bookDose2RequestDto) {

        Optional<Person> optionalPerson = personRepository.findById(bookDose2RequestDto.getPersonId());

        if(!optionalPerson.isPresent()){
            throw new PersonNotFoundException("Person Id Does not matched");
        }

        Person person = optionalPerson.get();

        if(!person.isDose1Taken()){
            throw new Dose1NotCompletedException("Your Dose 1 Not Completed, So Take Dose 1 first then only you will eligible for take dose 2");
        }

        if(person.isDose2Taken()){
            throw new DoseAlreadyTakenException("Dose is already taken");
        }



        Dose dose = new Dose();
        dose.setDoseType(bookDose2RequestDto.getDoseType());
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setPerson(person);

        person.setDose2Taken(true);
        person.getDoseTaken().add(dose);

        Person savedPerson = personRepository.save(person);


        List<Dose> doses = savedPerson.getDoseTaken();

        Dose savedDose = savedPerson.getDoseTaken().get(doses.size()-1);


//        convert entity to response dto

        BookDose2ResponseDto bookDose2ResponseDto = new BookDose2ResponseDto();
        bookDose2ResponseDto.setName(person.getName());
        bookDose2ResponseDto.setMessage("Yeah..! Congrats Your second dose of " + savedDose.getDoseType() + " is Done.");


        return bookDose2ResponseDto;


    }
}

















