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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

//    this is created by java mail sender dependency we just have to access it throw autowired
    @Autowired
    JavaMailSender javaMailSender;


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

        VaccinationCenter center = doctor.getCenter();


//        mail sending part
        String text = "Hello.. " + savedPerson.getName() + " your Appointment is Booked with " + savedDoctor.getName() + " And your vaccination center is "
                + center.getCenterName() + " please reach there at the time " + savedAppointment.getAppointmentDate();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("VaccinationBooking123@gmail.com");
        simpleMailMessage.setTo(savedPerson.getEmailId());
        simpleMailMessage.setSubject("Congrats..! your Appointment is Booked Successfully");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

//        prepare the response dto

        BookAppointmentResponseDto bookAppointmentResponseDto = new BookAppointmentResponseDto();

//        first get the center to set the attribut of centerresponse dto



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

    public List<BookAppointmentResponseDto> getAllAppintmentsOfDoctor(int doctorId) {

        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);

        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("Invalid Doctor id");
        }

        Doctor doctor = optionalDoctor.get();

        VaccinationCenter center = doctor.getCenter();

        List<Appointment> appointments = doctor.getAppointments();

        List<BookAppointmentResponseDto> dtos = new ArrayList<>();

        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);

            CenterResponseDto centerResponseDto = new CenterResponseDto();
            centerResponseDto.setCenterType(center.getCenterType());
            centerResponseDto.setCenterName(center.getCenterName());
            centerResponseDto.setAddress(center.getAddress());


            BookAppointmentResponseDto bookAppointmentResponseDto = new BookAppointmentResponseDto();
            bookAppointmentResponseDto.setAppointmentDate(appointment.getAppointmentDate());
            bookAppointmentResponseDto.setPersonName(appointment.getPerson().getName());
            bookAppointmentResponseDto.setDoctorName(appointment.getDoctor().getName());
            bookAppointmentResponseDto.setAppointmentId(appointment.getAppointmentId());
            bookAppointmentResponseDto.setCenterResponseDto(centerResponseDto);

            dtos.add(bookAppointmentResponseDto);
        }

        return dtos;
    }

    public List<BookAppointmentResponseDto> getAllAppintmentsOfPerson(int personId) {


        Optional<Person> optionalPerson = personRepository.findById(personId);

        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundException("Invalid Person Id");
        }

        Person person = optionalPerson.get();

        List<Appointment> appointments = person.getAppointments();

        List<BookAppointmentResponseDto> dtos = new ArrayList<>();

        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);

            Doctor doctor = appointment.getDoctor();
            VaccinationCenter center = doctor.getCenter();

            CenterResponseDto centerResponseDto = new CenterResponseDto();
            centerResponseDto.setCenterType(center.getCenterType());
            centerResponseDto.setCenterName(center.getCenterName());
            centerResponseDto.setAddress(center.getAddress());


            BookAppointmentResponseDto bookAppointmentResponseDto = new BookAppointmentResponseDto();
            bookAppointmentResponseDto.setAppointmentDate(appointment.getAppointmentDate());
            bookAppointmentResponseDto.setPersonName(appointment.getPerson().getName());
            bookAppointmentResponseDto.setDoctorName(appointment.getDoctor().getName());
            bookAppointmentResponseDto.setAppointmentId(appointment.getAppointmentId());
            bookAppointmentResponseDto.setCenterResponseDto(centerResponseDto);

            dtos.add(bookAppointmentResponseDto);
        }

        return dtos;
    }
}
