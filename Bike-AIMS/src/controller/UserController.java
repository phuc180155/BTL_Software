package controller;

import accessor.RentAccessor;
import entity.Rent;

import java.util.List;

public class UserController {
    private RentAccessor rentAccessor;

    public UserController(){
        this.rentAccessor = new RentAccessor();
    }

    public List<Rent> getRentByUserId(int userId){
        return this.rentAccessor.getRentByUserId(userId);
    }
}
