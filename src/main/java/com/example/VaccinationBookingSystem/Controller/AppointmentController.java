package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Service.AppointmentService;
import com.example.VaccinationBookingSystem.dto.RequestDto.BookAppointmentRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.BookAppointmentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity bookAppointment(@RequestBody BookAppointmentRequestDto bookAppointmentRequestDto){
        try {
            BookAppointmentResponseDto responseDto = appointmentService.bookAppointment(bookAppointmentRequestDto);
        return new ResponseEntity(responseDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    // get all the appointments of a particular doctor

    @GetMapping("/get_all_the_appointments_of_doctor")
    public ResponseEntity getAllAppintmentsOfDoctor(@RequestParam("doctorId") int doctorId){
        try {
            List<BookAppointmentResponseDto> responseDtos = appointmentService.getAllAppintmentsOfDoctor(doctorId);
            return new ResponseEntity(responseDtos,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    // get all the appointments for a particular person

    @GetMapping("/get_all_the_appointments_of_person")
    public ResponseEntity getAllAppintmentsOfPerson(@RequestParam("personId") int personId){
        try {
            List<BookAppointmentResponseDto> responseDtos = appointmentService.getAllAppintmentsOfPerson(personId);
            return new ResponseEntity(responseDtos,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
