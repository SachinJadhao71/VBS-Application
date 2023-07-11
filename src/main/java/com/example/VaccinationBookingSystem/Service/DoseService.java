package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Enum.DoseType;
import com.example.VaccinationBookingSystem.Exceptions.DoseAlreadyTakenException;
import com.example.VaccinationBookingSystem.Exceptions.PersonNotFoundException;
import com.example.VaccinationBookingSystem.Model.Dose;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Repository.DoseRepository;
import com.example.VaccinationBookingSystem.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {

    @Autowired
    DoseRepository doseRepository;

    @Autowired
    PersonRepository personRepository;

    public Dose getDose1(int personId, DoseType doseType) {

//        check that person exists or not
        Optional<Person> optionalPerson = personRepository.findById(personId);

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
        dose.setDoseType(doseType);
        dose.setPerson(person);


        person.setDose1Taken(true);
        personRepository.save(person);

        Dose doseTaken = doseRepository.save(dose);

        return doseTaken;





    }
}
