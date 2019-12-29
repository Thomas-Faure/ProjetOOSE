package DAO;

import BuisnessLogic.Ressource.AbstractResource;
import BuisnessLogic.Ressource.Resource;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;
import BuisnessLogic.Task.TaskState;
import BuisnessLogic.User.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RessourceDAOMySQL implements RessourceDAO {

    private static final String INSERT = "INSERT INTO resource (path, filename) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE resource SET path=?, filename=? WHERE resourceID=?";
    private static final String DELETE = "DELETE FROM resource WHERE resourceID=?";
    private static final String RESOURCEBYID = "SELECT * FROM resource where resourceID=?";
    private static final String RESOURCEBYPROJECT = "SELECT resourceID FROM resource_project WHERE projectID=?";

    public RessourceDAOMySQL(){
    }

    @Override
    public boolean save(AbstractResource resource) {
        boolean success = false;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
            ps.setString(1, resource.getPath());
            ps.setString(2, resource.getFilename());
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
    public boolean update(AbstractResource resource) {
        boolean success = false;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(UPDATE);
            ps.setString(1, resource.getPath());
            ps.setString(2, resource.getFilename());
            ps.setInt(3,resource.getResourceID());
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
    public boolean delete(int resourceID) {
        boolean success = false;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(DELETE);
            ps.setInt(1,resourceID);
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
    public AbstractResource getResourceById(int resourceID) {
        AbstractResource newResource = null;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(RESOURCEBYID);
            ps.setInt(1, resourceID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                newResource = new Resource(
                        rs.getInt("resourceID"),
                        rs.getString("path"),
                        rs.getString("filename"));
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newResource;
    }

    @Override
    public List<AbstractResource> getAllResourceByProject(int projectID) {
        List<AbstractResource> resourceList = new ArrayList<AbstractResource>();
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(RESOURCEBYPROJECT);
            ps.setInt(1, projectID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int newResourceID = rs.getInt("resourceID");
                AbstractResource newResource = getResourceById(newResourceID);
                resourceList.add(newResource);
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resourceList;
    }

}
