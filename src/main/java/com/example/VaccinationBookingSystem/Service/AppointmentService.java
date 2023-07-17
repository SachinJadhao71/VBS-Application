package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Exceptions.DoctorNotFoundException;
import com.example.VaccinationBookingSystem.Exceptions.PersonNotFoundException;
import com.example.VaccinationBookingSystem.Model.Appointment;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.Person;
import com.example.VaccinationBookingSystem.Model.VaccinationCenter;
import com.example.VaccinationBookingSystem.Repository.AppointmentRepository;
import com.example.VaccinationBookingSystem.Repository.DoctorRepository;
import com.example.VaccinationBookingSystem.Repository.PersonRepository;
import com.example.VaccinationBookingSystem.dto.RequestDto.BookAppointmentRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.BookAppointmentResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CenterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AppointmentRepository appointmentRepository;


    public BookAppointmentResponseDto bookAppointment(BookAppointmentRequestDto bookAppointmentRequestDto) {

//        check person and doctor exist or not

        Optional<Person> optionalPerson = personRepository.findById(bookAppointmentRequestDto.getPersonId());

        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundException("Invalid person Id");
        }

        Optional<Doctor> optionalDoctor = doctorRepository.findById(bookAppointmentRequestDto.getDoctorId());

        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("Invalid Doctor Id");
        }

        Person person = optionalPerson.get();
        Doctor doctor = optionalDoctor.get();

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));
        appointment.setDoctor(doctor);
        appointment.setPerson(person);


//        if we save both parent first then it will create two different Id after saved so we will first save the child then parent

        Appointment savedAppointment = appointmentRepository.save(appointment);

        person.getAppointments().add(savedAppointment);
        doctor.getAppointments().add(savedAppointment);

        Person savedPerson = personRepository.save(person);
        Doctor savedDoctor = doctorRepository.save(doctor);

//        prepare the response dto

        BookAppointmentResponseDto bookAppointmentResponseDto = new BookAppointmentResponseDto();

//        first get the center to set the attribut of centerresponse dto

        VaccinationCenter center = doctor.getCenter();

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setAddress(center.getAddress());
        centerResponseDto.setCenterName(center.getCenterName());
        centerResponseDto.setCenterType(center.getCenterType());


        bookAppointmentResponseDto.setPersonName(savedPerson.getName());
        bookAppointmentResponseDto.setDoctorName(savedDoctor.getName());
        bookAppointmentResponseDto.setAppointmentId(savedAppointment.getAppointmentId());
        bookAppointmentResponseDto.setAppointmentDate(savedAppointment.getAppointmentDate());
        bookAppointmentResponseDto.setCenterResponseDto(centerResponseDto);

        return bookAppointmentResponseDto;


    }
}
