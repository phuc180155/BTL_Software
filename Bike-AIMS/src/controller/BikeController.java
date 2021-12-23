package controller;

import accessor.BikeAccessor;
import accessor.DockAccessor;
import entity.Bike;
import entity.Dock;

import java.util.List;

public class BikeController extends BaseController{
    private DockAccessor dockAccessor;
    private BikeAccessor bikeAccessor;

    public BikeController(){
        this.dockAccessor = new DockAccessor();
        this.bikeAccessor = new BikeAccessor();
    }
}