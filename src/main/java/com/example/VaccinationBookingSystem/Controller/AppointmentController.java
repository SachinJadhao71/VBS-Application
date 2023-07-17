package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Service.AppointmentService;
import com.example.VaccinationBookingSystem.dto.RequestDto.BookAppointmentRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.BookAppointmentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
