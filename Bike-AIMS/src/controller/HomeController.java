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
    /**
     * this method gets all Media in DB and return back to home to display
     * @return List[Media]
     * @throws SQLException
     */
//    public List getAllMedia() throws SQLException{
//        return new Media().getAllMedia();
//    }

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
