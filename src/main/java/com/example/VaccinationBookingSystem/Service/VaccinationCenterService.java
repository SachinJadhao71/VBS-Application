package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Controller.VaccinationController;
import com.example.VaccinationBookingSystem.Enum.CenterType;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.VaccinationCenter;
import com.example.VaccinationBookingSystem.Repository.VaccinationCenterRepository;
import com.example.VaccinationBookingSystem.dto.RequestDto.CenterRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CenterResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CenterWithHighestDoctorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getAllTheDoctorsOfParticularCenterType(CenterType centerType) {

//        get the list of particular center type

        List<VaccinationCenter> centers = centerRepository.findAll();

        List<String> ans = new ArrayList<>();

        for(int i=0; i<centers.size(); i++){
            VaccinationCenter center = centers.get(i);

            if(center.getCenterType() == centerType){
                List<Doctor> doctors = center.getDoctors();

                for(int j=0; j<doctors.size(); j++){
                    ans.add(doctors.get(j).getName());
                }
            }
            else{
                continue;
            }
        }

        return ans;


    }

    public CenterWithHighestDoctorResponseDto getTheCenterWithHighestDoctors() {

        List<VaccinationCenter> centers = centerRepository.findAll();

        int max = 0;
        VaccinationCenter center = new VaccinationCenter();

        for(int i=0; i< centers.size(); i++){
            VaccinationCenter vc = centers.get(i);

            if(vc.getDoctors().size() > max){
                max = vc.getDoctors().size();
                center = vc;
            }
        }

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterType(center.getCenterType());
        centerResponseDto.setCenterName(center.getCenterName());
        centerResponseDto.setAddress(center.getAddress());

        CenterWithHighestDoctorResponseDto responseDto = new CenterWithHighestDoctorResponseDto();

        responseDto.setTotalDoctor(center.getDoctors().size());
        responseDto.setCenterResponseDto(centerResponseDto);

        return responseDto;
    }

    public CenterWithHighestDoctorResponseDto getTheCenterWithHighestDoctorOfParticularCenterType(CenterType centerType) {

        List<VaccinationCenter> centers = centerRepository.findAll();
        int max = 0;

        VaccinationCenter center = new VaccinationCenter();

        for (int i = 0; i < centers.size(); i++) {
            VaccinationCenter vc = centers.get(i);

            if(vc.getCenterType()==centerType && vc.getDoctors().size() > max){
                max = vc.getDoctors().size();
                center = vc;
            }
        }

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setAddress(center.getAddress());
        centerResponseDto.setCenterName(center.getCenterName());
        centerResponseDto.setCenterType(center.getCenterType());

        CenterWithHighestDoctorResponseDto responseDto = new CenterWithHighestDoctorResponseDto();
        responseDto.setTotalDoctor(center.getDoctors().size());
        responseDto.setCenterResponseDto(centerResponseDto);

        return responseDto;
    }
}
