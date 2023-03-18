package com.swedbank.StudentApplication.building;

public class RoomsNotFound extends RuntimeException{

    public RoomsNotFound(int id ){
        super("No rooms for building " + id);
    }

}
