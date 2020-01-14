package DAO.Resource;

import BusinessLogic.Ressource.AbstractResource;
import BusinessLogic.Ressource.Resource;
import DAO.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public class ResourceDAOMySQL implements IResourceDAO {

    private static final String INSERT = "INSERT INTO resource (path, filename, idProject) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE resource SET path=?, filename=? WHERE idResource=?";
    private static final String DELETE = "DELETE FROM resource WHERE idResource=?";
    private static final String RESOURCEBYID = "SELECT * FROM resource where idResource=?";
    private static final String RESOURCEBYPROJECT = "SELECT idResource FROM resource WHERE idProject=?";

    public ResourceDAOMySQL(){
    }

    @Override
    public boolean save(AbstractResource resource) {
        boolean success = false;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
            ps.setString(1, resource.getPath());
            ps.setString(2, resource.getFilename());
            ps.setInt(3, resource.getIdProject());
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
                        rs.getInt("idResource"),
                        rs.getString("path"),
                        rs.getString("filename"),
                        rs.getInt("idProject"));
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
                int newResourceID = rs.getInt("idResource");
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
