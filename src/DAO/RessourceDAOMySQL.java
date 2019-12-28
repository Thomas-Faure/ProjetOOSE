package DAO;

import BuisnessLogic.Ressource.Resource;
import BuisnessLogic.Task.Task;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RessourceDAOMySQL implements RessourceDAO {

    private static final String INSERT = "INSERT INTO RESOURCE (path, filename) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE RESOURCE SET path=?, filename=? WHERE resourceID=?";
    private static final String DELETE = "DELETE FROM RESOURCE WHERE resourceID=?";
    private static final String ALL = "SELECT * FROM RESOURCE";
    private static final String RESOURCEBYID = "SELECT * FROM RESOURCE where resourceID=?";


    public RessourceDAOMySQL(){
    }

    @Override
    public boolean save(Resource resource) {
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
            ps.setString(1, resource.getPath());
            ps.setString(2, resource.getFilename());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
