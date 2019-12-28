package DAO;

import BuisnessLogic.Sprint.AbstractSprint;

import java.util.List;

public class SprintDAOMySQL implements SprintDAO {

    private static final String INSERT = "INSERT INTO SPRINT (sprintName, beginDate, endDate) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE SPRINT SET sprintName=?, beginDate=?, endDate=? WHERE sprintID=?";
    private static final String DELETE = "DELETE FROM SPRINT WHERE sprintID=?";
    private static final String SPRINTBYID = "SELECT * FROM SPRINT where sprintID=?";
    //private static final String SPRINTSBYPROJECT = "";

    @Override
    public boolean save(AbstractSprint sprint) {
        return false;
    }

    @Override
    public boolean update(AbstractSprint sprint) {
        return false;
    }

    @Override
    public boolean delete(int sprintID) {
        return false;
    }

    @Override
    public AbstractSprint getSprintById(int sprintID) {
        return null;
    }

    @Override
    public List<AbstractSprint> getAllSprintByProject(int projectID) {
        return null;
    }
}
