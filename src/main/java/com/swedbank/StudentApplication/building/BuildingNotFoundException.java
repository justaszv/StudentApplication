package com.swedbank.StudentApplication.building;

public class BuildingNotFoundException extends RuntimeException{

    public BuildingNotFoundException(int id ){
        super("No building found for  " + id);
    }
}
