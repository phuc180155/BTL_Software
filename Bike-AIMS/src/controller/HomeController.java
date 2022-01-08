package controller;

import java.sql.SQLException;
import java.util.List;

import accessor.DockAccessor;
import entity.Dock;

/**
 * This class controls the flow of events in homescreen
 * @author nguyenlm
 */
public class HomeController extends BaseController{

    private DockAccessor dockAccessor;


    public HomeController(){
        this.dockAccessor = new DockAccessor();
    }

    public List<Dock> getAllDock() throws SQLException{
        return this.dockAccessor.getAll();
    }
    public List<Dock> searchDock(int searchOption, String info){
        return this.dockAccessor.searchDock(searchOption, info);
    }


}
