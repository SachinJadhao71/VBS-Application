package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Enum.CenterType;
import com.example.VaccinationBookingSystem.Service.VaccinationCenterService;
import com.example.VaccinationBookingSystem.dto.RequestDto.CenterRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CenterResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CenterWithHighestDoctorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
public class VaccinationController {

    @Autowired
    VaccinationCenterService centerService;

    @PostMapping("/add")
    public ResponseEntity addCenter(@RequestBody CenterRequestDto centerRequestDto){
        CenterResponseDto responseDto = centerService.addCenter(centerRequestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }

    // get all the doctors at centers of a particular centerType

    @GetMapping("/get_all_the_doctors_from_particular_centerType")
    public ResponseEntity getAllTheDoctorsOfParticularCenterType(@RequestParam("centerType") CenterType centerType){
        List<String> ans = centerService.getAllTheDoctorsOfParticularCenterType(centerType);

        return new ResponseEntity<>(ans,HttpStatus.ACCEPTED);
    }

    // get the center with highest number of doctors

    @GetMapping("/get_the_center_with_highest_doctor")
    public ResponseEntity getTheCenterWithHighestDoctors(){
        CenterWithHighestDoctorResponseDto responseDto = centerService.getTheCenterWithHighestDoctors();

        return new ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }

    // get the center with highest number of doctors among a particular centerType

    @GetMapping("/get_the_center_of_particular_centerType_with_highest_doctor")
    public ResponseEntity getTheCenterWithHighestDoctorOfParticularCenterType(@RequestParam("centerType") CenterType centerType){
        CenterWithHighestDoctorResponseDto responseDto = centerService.getTheCenterWithHighestDoctorOfParticularCenterType(centerType);

        return new ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }
}
