package controller;

import accessor.BikeAccessor;
import accessor.DockAccessor;
import entity.Bike;
import entity.Dock;

import java.util.List;

public class DockController extends BaseController{
    private DockAccessor dockAccessor;
    private BikeAccessor bikeAccessor;

    public DockController(){
        this.dockAccessor = new DockAccessor();
        this.bikeAccessor = new BikeAccessor();
    }

    public Dock getDock(int dockId) {
        return this.dockAccessor.get(dockId);
    }

    public List<Bike> getBikeByDockId(int dockId){
        return this.bikeAccessor.getBikeByDockId(dockId);
    }

    public List<Bike> searchBike(String option, String info, int dockId){
        return this.bikeAccessor.searchBike(option, info, dockId);
    }
}