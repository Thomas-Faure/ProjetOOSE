package DAO.User.GlobalUser;

import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.GlobalUser;
import BuisnessLogic.User.Member;
import BuisnessLogic.User.User;
import DAO.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GlobalUserDAOMySQL implements GlobalUserDAO {

    private static final String INSERT = "INSERT INTO user (username, password, firstName, lastName, city, phoneNumber, email, position, isAdmin ) VALUES (?, ?, ?, ?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE user SET username=?, password=?, firstName=?, lastName=?, city=?, phoneNumber=?, email=?, position=?, isAdmin=? WHERE idUser=?";
    private static final String DELETE = "DELETE FROM user WHERE idUser=?";
    private static final String ALL = "SELECT * from user";
    private static final String USERBYID = "SELECT * from user where idUser=?";

    public GlobalUserDAOMySQL() {

    }
    @Override
    public User createUser(String username, String password) {
        User user=null;
        try {
            //String query = "SELECT * FROM user WHERE username = 'lauren' and password = 'lauren';";
            String query = "SELECT * FROM user WHERE username = '" +  username + "' and password = '" + password + "';";
            System.out.println(query);
            ResultSet result = MySQLConnector.getSQLConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(query);
            if(result.first()) {
                System.out.println("correct");
                user= new User( result.getInt("idUser"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("city"),
                        result.getString("phoneNumber"),
                        result.getString("email"),
                        result.getString("position"),
                        result.getBoolean("isAdmin")
                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public boolean save(AbstractUser user) {
        try {

            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getCity());
            ps.setString(6, user.getPhoneNumber());
            ps.setString(7, user.getEmail());
            ps.setString(8, user.getPosition());
            ps.setBoolean(9, user.isAdmin());
            ps.executeUpdate();
            ps.close();

            System.out.println("Nouvel utilisateur dans la base: " + user.toString());
            return true;
        } catch (SQLException e) {

            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(AbstractUser user) {
        try {

            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(UPDATE);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getCity());
            ps.setString(6, user.getPhoneNumber());
            ps.setString(7, user.getEmail());
            ps.setString(8, user.getPosition());
            ps.setBoolean(9, user.isAdmin());
            ps.setInt(10, user.getId());

            int i = ps.executeUpdate();
            ps.close();
            System.out.println("L'utilisateur " + user.getId() + " contient maintenant: " + user.toString());
            return i > 0;

            //return true;
        } catch (SQLException e) {

            System.out.println(e);
            return false;
        }

    }
    @Override
    public boolean delete(int id) {

        try {

            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(DELETE);

            ps.setInt(1, id);

            int i = ps.executeUpdate();
            ps.close();
            System.out.println("User with id: " + id + " was sucesfully deleted from DB.");
            return i > 0;



        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AbstractUser> getAllUsers() {
        List<AbstractUser> list = new ArrayList<>();
        try {

            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALL);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                User user = new GlobalUser(
                        rs.getInt("idUser"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("city"),
                        rs.getString("phoneNumber"),
                        rs.getString("email"),
                        rs.getString("position"),
                        rs.getBoolean("isAdmin")

                );
                list.add(user);
            }
            ps.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public AbstractUser getUserById(int id) {
        AbstractUser user = null;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(USERBYID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = new GlobalUser(
                        rs.getInt("idUser"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("city"),
                        rs.getString("phoneNumber"),
                        rs.getString("email"),
                        rs.getString("position"),
                        rs.getBoolean("isAdmin")

                );

            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public List<Member> getUserByName(String name) {
        return null;
    }
}
