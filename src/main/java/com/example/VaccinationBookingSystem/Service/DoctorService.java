package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Exceptions.CenterNotFoundException;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Model.VaccinationCenter;
import com.example.VaccinationBookingSystem.Repository.DoctorRepository;
import com.example.VaccinationBookingSystem.Repository.VaccinationCenterRepository;
import com.example.VaccinationBookingSystem.dto.RequestDto.AddDoctorRequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.AddDoctorResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.CenterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    VaccinationCenterRepository centerRepository;

    public AddDoctorResponseDto addDoctor(AddDoctorRequestDto addDoctorRequestDto) {

        Optional<VaccinationCenter> optionalcenter = centerRepository.findById(addDoctorRequestDto.getCenterId());

        if(optionalcenter.isEmpty()){
            throw new CenterNotFoundException("Sorry wrong centerId, please provide correct Center Id");
        }

        VaccinationCenter center = optionalcenter.get();



        Doctor doctor = new Doctor();
        doctor.setAge(addDoctorRequestDto.getAge());
        doctor.setName(addDoctorRequestDto.getName());
        doctor.setEmailId(addDoctorRequestDto.getEmailId());
        doctor.setGender(addDoctorRequestDto.getGender());

//        set the center to the doctor
        doctor.setCenter(center);

//        in center entity there is list of doctors so add this doctor to that list

        center.getDoctors().add(doctor);

//        only save the parent and child will automatically save

        VaccinationCenter savedCenter = centerRepository.save(center);

//        get the docotr which we have save just from the parent entity

        List<Doctor> doctors = savedCenter.getDoctors();

        Doctor savedDoctor = doctors.get(doctors.size()-1);

//        prepare response dto

        AddDoctorResponseDto addDoctorResponseDto = new AddDoctorResponseDto();

        addDoctorResponseDto.setName(savedDoctor.getName());
        addDoctorResponseDto.setMessage("Your Registration has been done");

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterType(savedCenter.getCenterType());
        centerResponseDto.setCenterName(savedCenter.getCenterName());
        centerResponseDto.setAddress(savedCenter.getAddress());

        addDoctorResponseDto.setCenterResponseDto(centerResponseDto);

        return addDoctorResponseDto;





    }
}
