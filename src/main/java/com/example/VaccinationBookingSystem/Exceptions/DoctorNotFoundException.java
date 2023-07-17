package com.example.VaccinationBookingSystem.Exceptions;

public class DoctorNotFoundException extends RuntimeException{
    public DoctorNotFoundException(String message){
        super(message);
    }
}
