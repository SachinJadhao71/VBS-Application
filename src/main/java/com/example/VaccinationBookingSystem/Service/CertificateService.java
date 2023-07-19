package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Exceptions.Dose1NotCompletedException;
import com.example.VaccinationBookingSystem.Exceptions.Dose2NotCompletedException;
import com.example.VaccinationBookingSystem.Exceptions.PersonNotFoundException;
import com.example.VaccinationBookingSystem.Model.Certificate;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.Dose;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Repository.CertificateRepository;
import com.example.VaccinationBookingSystem.Repository.PersonRepository;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CertificateResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CertificateService {

    @Autowired
    CertificateRepository certificateRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    JavaMailSender javaMailSender;
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
        certificate.setConfirmationMessage("Your Certificate is Successfully Generated");


        Dose dose1 = person.getDoseTaken().get(0);
        Dose dose2 = person.getDoseTaken().get(1);


        Certificate savedCertificate = certificateRepository.save(certificate);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Congrats Your Vaccination Certificate Is Generated..!");
        simpleMailMessage.setFrom("VaccinationBooking123@gmail.com");
        simpleMailMessage.setTo(person.getEmailId());
        simpleMailMessage.setText("Congratulation Mr/Mrs. " + person.getName() + " " + System.lineSeparator() + "You Have Taken Both Doses " +
                "That's why this certificate is given to you," + System.lineSeparator() + "Thank you for cooprate to the government " +
                "And if any of your friend or relative are still left from vaccination so please inform them " +
                "to take dose and save himself and family. " + System.lineSeparator() + "Thank you... Stay Vaccinated and Stay Healthy...!" +
                System.lineSeparator() +  "Your Information Of Doses as follows : " + System.lineSeparator() + "Dose1 Type : " + dose1.getDoseType() + System.lineSeparator() +
                "Dose1 Date : " + dose1.getVaccinationDate() + System.lineSeparator() +
                "Dose2 Type : " + dose2.getDoseType() + System.lineSeparator() + "Dose2 Date :  " + dose2.getVaccinationDate() + System.lineSeparator() +"Dhanyawad..!!!");

        javaMailSender.send(simpleMailMessage);


        CertificateResponseDto responseDto = new CertificateResponseDto();
        responseDto.setMessage("Congrats...!  Mr/Mrs. " + person.getName() + " You Have Taken Both Doses Successfully, That's why" +
                " this certificate is given to you And Now You are eligible to take Booster Dose, If Any of your Friend or " +
                "Relative Not Taken any Dose then inform them to cooprate to Goverment and this is beneficial to you and your family only " +
                " Thank you...!  Stay vaccinated And Stay healthy");
        responseDto.setDose1Date(dose1.getVaccinationDate());
        responseDto.setDose1Type(dose1.getDoseType());

        responseDto.setDose2Date(dose2.getVaccinationDate());
        responseDto.setDose2Type(dose2.getDoseType());

        return responseDto;

    }
}
