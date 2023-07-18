package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Exceptions.Dose1NotCompletedException;
import com.example.VaccinationBookingSystem.Exceptions.Dose2NotCompletedException;
import com.example.VaccinationBookingSystem.Exceptions.PersonNotFoundException;
import com.example.VaccinationBookingSystem.Model.Certificate;
import com.example.VaccinationBookingSystem.Model.Dose;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Repository.CertificateRepository;
import com.example.VaccinationBookingSystem.Repository.PersonRepository;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CertificateResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CertificateService {

    @Autowired
    CertificateRepository certificateRepository;

    @Autowired
    PersonRepository personRepository;
    public CertificateResponseDto generateCertificate(int personId) {

        Optional<Person> optionalPerson = personRepository.findById(personId);

        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundException("Invalid Person Id");
        }

        Person person = optionalPerson.get();

//        check dose one and dose 2 are complete or not
        if(!person.isDose1Taken()){
            throw new Dose1NotCompletedException("Dose 1 of "+ person.getName() +" is Not Completed.");
        }

        if(!person.isDose2Taken()){
            throw new Dose2NotCompletedException("Dose 2 of "+ person.getName() +" is Not Completed.");
        }

        Certificate certificate = new Certificate();
        certificate.setCertificateNo(String.valueOf(UUID.randomUUID()));
        certificate.setPerson(person);
        certificate.setConfirmationMessage("This is Certified that your Both Doses are successfully completed" +
                " that's why this certificate is Given to you By Government Of India");

//        int size = person.getDoseTaken().size();
//        Dose dose1 = person.getDoseTaken().get(size-2);
//        Dose dose2 = person.getDoseTaken().get(size-1);


        Certificate savedCertificate = certificateRepository.save(certificate);

        CertificateResponseDto responseDto = new CertificateResponseDto();
        responseDto.setMessage("Congrats..! Your Both Doses are Complete");
        responseDto.setPersonName(person.getName());
//        responseDto.setDose1Date(dose1.getVaccinationDate());
//        responseDto.setDose2Date(dose2.getVaccinationDate());

        return responseDto;

    }
}
