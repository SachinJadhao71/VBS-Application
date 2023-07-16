package com.example.VaccinationBookingSystem.Exceptions;

public class Dose1NotCompletedException extends RuntimeException{
    public Dose1NotCompletedException(String message){
        super(message);
    }
}
