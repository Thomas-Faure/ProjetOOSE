package DAO;


import BuisnessLogic.Sprint.AbstractSprint;
import BuisnessLogic.Sprint.Sprint;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SprintDAOMySQL implements SprintDAO {

    private static final String INSERT = "INSERT INTO sprint (sprintName, beginDate, endDate) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE sprint SET sprintName=?, beginDate=?, endDate=? WHERE sprintID=?";
    private static final String DELETE = "DELETE FROM sprint WHERE sprintID=?";
    private static final String SPRINTBYID = "SELECT * FROM sprint WHERE sprintID=?";
    private static final String SPRINTSBYPROJECT = "SELECT sprintID FROM sprint_project WHERE projectID=?";

    public SprintDAOMySQL(){
    }

    @Override
    public boolean save(AbstractSprint sprint) {
        boolean success = false;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
            ps.setString(1, sprint.getSprintName());
            ps.setDate(2, java.sql.Date.valueOf(sprint.getBeginDate()));
            ps.setDate(3, java.sql.Date.valueOf(sprint.getEndDate()));
            int i = ps.executeUpdate();
            ps.close();
            if (i > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean update(AbstractSprint sprint) {
        boolean success = false;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(UPDATE);
            ps.setString(1, sprint.getSprintName());
            ps.setDate(2, java.sql.Date.valueOf(sprint.getBeginDate()));
            ps.setDate(3, java.sql.Date.valueOf(sprint.getEndDate()));
            ps.setInt(4, sprint.getSprintID());
            int i = ps.executeUpdate();
            ps.close();
            if (i > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean delete(int sprintID) {
        boolean success = false;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(DELETE);
            ps.setInt(1, sprintID);
            int i = ps.executeUpdate();
            ps.close();
            if (i > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public AbstractSprint getSprintById(int sprintID) {
        AbstractSprint newSprint = null;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(SPRINTBYID);
            ps.setInt(1, sprintID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                newSprint = new Sprint(
                        rs.getInt("sprintID"),
                        rs.getString("sprintName"),
                        rs.getDate("beginDate").toLocalDate(),
                        rs.getDate("endDate").toLocalDate());
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newSprint;
    }

    @Override
    public List<AbstractSprint> getAllSprintByProject(int projectID) {
        List<AbstractSprint> sprintList = new ArrayList<AbstractSprint>();
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(SPRINTSBYPROJECT);
            ps.setInt(1, projectID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int newSprintID = rs.getInt("sprintID");
                AbstractSprint newSprint = getSprintById(newSprintID);
                sprintList.add(newSprint);
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sprintList;
    }
}
