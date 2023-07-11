package com.example.VaccinationBookingSystem.Exceptions;

public class DoseAlreadyTakenException extends RuntimeException{

    public DoseAlreadyTakenException(String message){
        super(message);
    }
}
