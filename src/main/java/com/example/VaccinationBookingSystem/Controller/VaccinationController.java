package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Service.VaccinationCenterService;
import com.example.VaccinationBookingSystem.dto.RequestDto.CenterRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CenterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
