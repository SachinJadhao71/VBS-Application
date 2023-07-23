package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Enum.Gender;
import com.example.VaccinationBookingSystem.Exceptions.EmailNotFoundException;
import com.example.VaccinationBookingSystem.Exceptions.PersonNotFoundException;
import com.example.VaccinationBookingSystem.Exceptions.personAlreadyExistsException;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Repository.PersonRepository;
import com.example.VaccinationBookingSystem.dto.RequestDto.AddPersonRequestdto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.AddPersonResponsedto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.PersonResponseDto;
import com.example.VaccinationBookingSystem.transformer.PersonTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public AddPersonResponsedto addPerson(AddPersonRequestdto addPersonRequestdto) {

//        we cannot save dto directly because databse dont know dto he will only understand entity
//        so we have to convert dto to the entity

        Person person = new Person();

        person.setName(addPersonRequestdto.getName());
        person.setAge(addPersonRequestdto.getAge());
        person.setEmailId(addPersonRequestdto.getEmailId());
        person.setGender(addPersonRequestdto.getGender());

        person.setDose1Taken(false);
        person.setDose2Taken(false);

        Person savedPerson = personRepository.save(person);

//        we have save person successfully but we dont want to show entire entity so we will create
//        response dto  and then we can return only necessory detail

        AddPersonResponsedto addPersonResponsedto = new AddPersonResponsedto();

        addPersonResponsedto.setName(savedPerson.getName());
        addPersonResponsedto.setMessage("Congrats..! your Registration has been successfully done");

        return addPersonResponsedto;


    }

    public String updateEmail(String oldEmail, String newEmail) {

        Person person = personRepository.findByEmailId(oldEmail);

        if(person == null){
            throw new EmailNotFoundException("oldEmail doesn't exists");
        }

        person.setEmailId(newEmail);

        personRepository.save(person);

        return "Your Email successfully updated";
    }

    public List<PersonResponseDto> getAllMalesOfGreaterThanCertainAge(int age) {
        List<Person> allPerson = personRepository.findByAge(age);

        List<PersonResponseDto> responseDtos = new ArrayList<>();

        for(Person person : allPerson){
            PersonResponseDto personResponseDto = PersonTransformer.PersonToPersonResponseDto(person);
            responseDtos.add(personResponseDto);
        }

        return responseDtos;
    }

    public List<PersonResponseDto> getAllFemalesWhoTakenOnlyDose1() {

        List<Person> femaleList = personRepository.findAllFemale(Gender.FEMALE);

        List<PersonResponseDto> responseDtos = new ArrayList<>();

        for (Person person : femaleList) {
            if (person.isDose1Taken() && !person.isDose2Taken()) {
                responseDtos.add(PersonTransformer.PersonToPersonResponseDto(person));
            }
        }
        return responseDtos;
    }

    public List<PersonResponseDto> getAllWhoTakeBothDoses() {

        List<Person> personList = personRepository.findAll();

        List<PersonResponseDto> responseDtoList = new ArrayList<>();

        for(Person person : personList){
            if(person.isDose1Taken() && person.isDose2Taken()){
                responseDtoList.add(PersonTransformer.PersonToPersonResponseDto(person));
            }
        }

        return responseDtoList;

    }

    public List<PersonResponseDto> getAllWhoHaveNotTakenAnyDose() {
        List<Person> personList = personRepository.findAllByVaccination();

        List<PersonResponseDto> responseDtoList = new ArrayList<>();

        for(Person person : personList){
            responseDtoList.add(PersonTransformer.PersonToPersonResponseDto(person));
        }

        return responseDtoList;

    }

    public List<PersonResponseDto>  getAllTheFamlesAboveTheCertainAgeWithDose1(int age) {

        List<Person> personList = personRepository.findByAgeAndByGender(age, Gender.FEMALE);

        List<PersonResponseDto> responseDtoList = new ArrayList<>();

        for(Person person : personList){
            if(person.isDose1Taken()){
                responseDtoList.add(PersonTransformer.PersonToPersonResponseDto(person));
            }
        }

        return responseDtoList;
    }

    public List<PersonResponseDto> getAllTheMalesAboveCertainAgeAndTakenBothDose(int age) {
        List<Person> personList = personRepository.findByAgeAndByGender(age,Gender.MALE);

        List<PersonResponseDto> responseDtoList = new ArrayList<>();

        for(Person person : personList){
            if(person.isDose1Taken() && person.isDose2Taken()){
                responseDtoList.add(PersonTransformer.PersonToPersonResponseDto(person));
            }
        }

        return responseDtoList;

    }
}
