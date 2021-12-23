package accessor;

import entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentAccessor extends DataAccessor<Rent>{
    @Override
    public Rent get(int id) {
        String q = "SELECT * FROM rent WHERE rentId = " + id;
        Rent rent = null;
        try{
            ResultSet rs = query(q);
            if(!rs.next()){
                return null;
            }
            BikeAccessor bikeAccessor = new BikeAccessor();
            Bike rentedBike = bikeAccessor.get(rs.getInt("bikeId"));
            rent = new Rent(rs.getInt("rentId"), rs.getInt("userId"), rentedBike,
                            rs.getInt("debit"), rs.getTimestamp("startTime"),
                            rs.getTimestamp("endTime"));
        } catch(Exception se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        return rent;
    }

    public List<Rent> getByQuery(String q){
        List<Rent> rentLst = new ArrayList<Rent>();
        try{
            ResultSet rs = query(q);
            BikeAccessor bikeAccessor = new BikeAccessor();
            while(rs.next()){
                Bike bike = bikeAccessor.get(rs.getInt("bikeId"));
                Bike rentedBike = bikeAccessor.get(rs.getInt("bikeId"));
                Rent rent = new Rent(rs.getInt("rentId"), rs.getInt("userId"), rentedBike,
                        rs.getInt("debit"), rs.getTimestamp("startTime"),
                        rs.getTimestamp("endTime"));
                rentLst.add(rent);
            }
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        return rentLst;
    }

    @Override
    public List<Rent> getAll() {
        String q = "SELECT * FROM rent" ;
        return this.getByQuery(q);
    }

    public List<Rent> getRentByUserId(int userId) {
        String q = "SELECT * FROM rent WHERE userId = " + userId;
        return this.getByQuery(q);
    }

    @Override
    public void update(Rent rent) {
        BikeAccessor bikeAccessor = new BikeAccessor();
        if(rent.getEndTime() == null){
            String q = "UPDATE rent SET " +
                    " startTime = " + "\"" + rent.getStartTime() + "\"" +
                    " , debit = " + rent.getDebit() +
                    " , userId = " + rent.getUserId() +
                    " , bikeId = " + rent.getRentedBike().getBikeId() +
                    " WHERE rentId = " + rent.getRentId();
            System.out.println(q);
            try{
                executeUpdate(q);
                // Cần update status cho bike nữa: bikeAccessor.update(rentedBike);
            } catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }
        }else{
            String q = "UPDATE rent SET " +
                    " startTime = " + "\"" + rent.getStartTime() + "\"" +
                    " endTime = " + "\"" + rent.getEndTime() + "\"" +
                    " , debit = " + rent.getDebit() +
                    " , userId = " + rent.getUserId() +
                    " , bikeId = " + rent.getRentedBike().getBikeId() +
                    " WHERE rentId = " + rent.getRentId();
            System.out.println(q);
            try{
                executeUpdate(q);
                // bikeAccessor.update(rentedBike);
            } catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }
        }
    }

    @Override
    public void save(Rent rent) {
        if(rent.getEndTime() == null){
            String q = "insert into rent(startTime, debit, userId, bikeId)\n" +
                    "values(" +
                    "\"" + rent.getStartTime() + "\" , " +
                    rent.getDebit() + ", " +
                    rent.getUserId() + ", " +
                    rent.getRentedBike().getBikeId() + ", " +
                    ")";
            System.out.println(q);
            try{
                executeUpdate(q);
            } catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }
        }else{
            String q = "insert into rent(startTime, endTime, debit, userId, bikeId)\n" +
                    "values(" +
                    "\"" + rent.getStartTime() + "\" , " +
                    "\"" + rent.getEndTime() + "\" , " +
                    rent.getDebit() + ", " +
                    rent.getUserId() + ", " +
                    rent.getRentedBike().getBikeId() + ", " +
                    ")";
            System.out.println(q);
            try{
                executeUpdate(q);
            } catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int rentId) {
        String q = "DELETE FROM rent WHERE rentId = " + rentId;
        System.out.println(q);
        try{
            execute(q);
        } catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
}
