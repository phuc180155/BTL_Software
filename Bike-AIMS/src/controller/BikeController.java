package controller;

import accessor.BikeAccessor;
import accessor.DockAccessor;

public class BikeController extends BaseController{
    private DockAccessor dockAccessor;
    private BikeAccessor bikeAccessor;

    public BikeController(){
        this.dockAccessor = new DockAccessor();
        this.bikeAccessor = new BikeAccessor();
    }
}