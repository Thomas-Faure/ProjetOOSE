package DAO.Role;

import BuisnessLogic.Role.AbstractRole;
import BuisnessLogic.Role.Role;
import DAO.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOMySQL implements RoleDAO {

    private static final String INSERT = "INSERT INTO role (name) VALUES (?)";
    private static final String UPDATE = "UPDATE role SET name=? WHERE idRole=?";
    private static final String DELETE = "DELETE FROM role WHERE idRole=?";
    private static final String ALL = "SELECT * from role";
    private static final String ROLEBYID = "SELECT * from role where idRole=?";

    public RoleDAOMySQL() {

    }

    public Role createRoleById(int id) {
        Role role=null;
        try {
            String query = "SELECT * FROM role WHERE id="+id;
            ResultSet result = MySQLConnector.getSQLConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(query);
            if(result.first()) {
                System.out.println("correct");
                //� changer
                role = new Role (
                        result.getInt("idRole"),
                        result.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public boolean save(AbstractRole role) {
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
            ps.setString(1, role.getName());
            ps.executeUpdate();
            ps.close();

            return true;
        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    public boolean update(AbstractRole role) {

        try {

            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(UPDATE);
            ps.setString(1, role.getName());
            ps.setInt(2, role.getId());

            int i = ps.executeUpdate();
            ps.close();
            return i > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(int id) {

        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(DELETE);
            ps.setInt(1, id);

            int i = ps.executeUpdate();
            ps.close();
            return i > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<AbstractRole> getAllRoles() {

        List<AbstractRole> list = new ArrayList<>();
        try {

            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALL);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                AbstractRole role = new Role(
                        rs.getInt("idRole"),
                        rs.getString("name")
                );
                list.add(role);
            }
            ps.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return list;
    }

    public AbstractRole getRoleById(int id) {
        AbstractRole role = null;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ROLEBYID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                role = new Role(
                        rs.getInt("idRole"),
                        rs.getString("name"));
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return role;
    }

    //Peut être non nécessaire
    public List<AbstractRole> getRoleByName(String name) {
        return null;
    }
}
