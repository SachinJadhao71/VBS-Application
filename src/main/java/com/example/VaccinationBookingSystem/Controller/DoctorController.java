package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Service.DoctorService;
import com.example.VaccinationBookingSystem.dto.RequestDto.AddDoctorRequestDto;
import com.example.VaccinationBookingSystem.dto.RequestDto.DoctorRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.AddDoctorResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.DoctorWithAppointmentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody AddDoctorRequestDto addDoctorRequestDto){

        try {
            AddDoctorResponseDto responseDto = doctorService.addDoctor(addDoctorRequestDto);
            return new ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // get the doctor with highest number of appointments

    @GetMapping("/get_doctor_with_highest_appointment")
    public ResponseEntity getDoctorWithHighestAppointment(){
        DoctorWithAppointmentResponseDto responseDto = doctorService.getDoctorWithHighestAppointment();

        return new ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }


    // get the list of doctors who belong to a particular center

    @GetMapping("/get_the_doctors_of_particular_center")
    public ResponseEntity getTheDoctorsFromParticularCenter(@RequestParam("centerId") int centerId){
        try {
            List<String> doctors = doctorService.getTheDoctorsFromParticularCenter(centerId);
            return new ResponseEntity<>(doctors,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // api to update email and/or age of a doctor

    @PutMapping("update_age_or_Email")
    public ResponseEntity updateEmailOrAge(@RequestBody DoctorRequestDto doctorRequestDto){
        try {
            String message = doctorService.updateEmailOrAge(doctorRequestDto);
            return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
