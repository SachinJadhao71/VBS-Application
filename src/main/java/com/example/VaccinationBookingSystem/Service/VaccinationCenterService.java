package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Model.VaccinationCenter;
import com.example.VaccinationBookingSystem.Repository.VaccinationCenterRepository;
import com.example.VaccinationBookingSystem.dto.RequestDto.CenterRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CenterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterService {

    @Autowired
    VaccinationCenterRepository centerRepository;
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto) {

        VaccinationCenter center = new VaccinationCenter();

        center.setCenterType(centerRequestDto.getCenterType());
        center.setCenterName(centerRequestDto.getCenterName());
        center.setAddress(centerRequestDto.getAddress());


        VaccinationCenter savedcenter = centerRepository.save(center);

        CenterResponseDto responseDto = new CenterResponseDto();

        responseDto.setAddress(savedcenter.getAddress());
        responseDto.setCenterName(savedcenter.getCenterName());
        responseDto.setCenterType(savedcenter.getCenterType());

        return responseDto;
    }
}
