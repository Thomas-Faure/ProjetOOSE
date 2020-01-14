package Facade;


import BusinessLogic.Sprint.AbstractSprint;
import DAO.MySQLDAOFactory;
import DAO.SprintDAO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public class SprintFacade implements ISprintFacade {
    private List<AbstractSprint> sprints;
    private SprintDAO dao;

    public static SprintFacade FacadeInstance;
    private SprintFacade(){
        this.dao = MySQLDAOFactory.getInstance().getSprintDAO();
        this.sprints = new ArrayList<AbstractSprint>();
    }

    public static SprintFacade getInstance(){
        if(FacadeInstance == null){
            FacadeInstance = new SprintFacade();
        }
        return FacadeInstance;
    }

    public SprintDAO getDao(){
        return this.dao;
    }

    public List<AbstractSprint> getListSprint(){
        return this.sprints;
    }

    @Override
    public boolean addSprint(AbstractSprint sprint) {
        if(FacadeInstance.getDao().save(sprint)){
            FacadeInstance.getListSprint().add(sprint);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateSprint(AbstractSprint sprint) {
        return FacadeInstance.getDao().update(sprint);
    }

    @Override
    public boolean deleteSprint(int sprintID) {
        return FacadeInstance.getDao().delete(sprintID);
    }

    @Override
    public AbstractSprint getSprintById(int sprintID) {
        return FacadeInstance.getDao().getSprintById(sprintID);
    }

    @Override
    public List<AbstractSprint> getListSprintByProject(int projectID) {
        return FacadeInstance.getDao().getAllSprintByProject(projectID);
    }



}
