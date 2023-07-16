package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Enum.DoseType;
import com.example.VaccinationBookingSystem.Exceptions.Dose1NotCompletedException;
import com.example.VaccinationBookingSystem.Exceptions.DoseAlreadyTakenException;
import com.example.VaccinationBookingSystem.Exceptions.PersonNotFoundException;
import com.example.VaccinationBookingSystem.Model.Dose;
import com.example.VaccinationBookingSystem.Service.DoseService;
import com.example.VaccinationBookingSystem.dto.RequestDto.BookDose1RequestDto;
import com.example.VaccinationBookingSystem.dto.RequestDto.BookDose2RequestDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.BookDose1ResponseDto;
import com.example.VaccinationBookingSystem.dto.ResponseDto.BookDose2ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;

//    @PostMapping("/get_Dose1")
//    public ResponseEntity getDose1(@RequestParam("Id") int personId, @RequestParam("doseType") DoseType doseType){
//        try {
//            Dose dose = doseService.getDose1(personId, doseType);
//            return new ResponseEntity(dose, HttpStatus.CREATED);
//        }
//        catch (DoseAlreadyTakenException d){
//            return new ResponseEntity(d.getMessage(),HttpStatus.BAD_REQUEST);
//        }
//        catch (PersonNotFoundException p) {
//            return new ResponseEntity(p.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }


    @PostMapping("/get_Dose1")
    public ResponseEntity getDose1(@RequestBody BookDose1RequestDto bookDose1RequestDto){
        try {
            BookDose1ResponseDto bookDose1ResponseDto = doseService.getDose1(bookDose1RequestDto);
            return new ResponseEntity(bookDose1ResponseDto, HttpStatus.CREATED);
        }
        catch (DoseAlreadyTakenException d){
            return new ResponseEntity(d.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (PersonNotFoundException p) {
            return new ResponseEntity(p.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    get dose 2
    @PostMapping("/get_Dose2")
    public ResponseEntity getDose2(@RequestBody BookDose2RequestDto bookDose2RequestDto){
        try {

            BookDose2ResponseDto responseDto = doseService.getDose2(bookDose2RequestDto);
            return new ResponseEntity(responseDto,HttpStatus.CREATED);
        }
        catch(PersonNotFoundException p){
            return new ResponseEntity(p.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch(Dose1NotCompletedException dd){
            return new ResponseEntity(dd.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch(DoseAlreadyTakenException d){
            return new ResponseEntity(d.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
