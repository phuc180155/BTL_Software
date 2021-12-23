package controller;

import accessor.RentAccessor;
import entity.Rent;
import entity.User;

import java.util.List;

/**
 * This class is the base controller for our AIMS project
 * @author nguyenlm
 */
public class UserController {
    private RentAccessor rentAccessor;

    public UserController(){
        this.rentAccessor = new RentAccessor();
    }

    public List<Rent> getRentByUserId(int userId){
        return this.rentAccessor.getRentByUserId(userId);
    }
}
