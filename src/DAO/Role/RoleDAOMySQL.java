package DAO.Role;

import BusinessLogic.Role.AbstractRole;
import BusinessLogic.Role.Role;
import DAO.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette Classe correspond au DAO qui gère les roles.
 * IL est en lien avec la base de données sur laquelle il fait des
 * requetes pour récupérer des données.
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class RoleDAOMySQL implements RoleDAO {

    private static final String INSERT = "INSERT INTO role (name) VALUES (?)";
    private static final String UPDATE = "UPDATE role SET name=? WHERE idRole=?";
    private static final String DELETE = "DELETE FROM role WHERE idRole=?";
    private static final String ALL = "SELECT * from role";
    private static final String ROLEBYID = "SELECT * from role where idRole=?";

    public RoleDAOMySQL() {

    }

    /**
     * Fonction non utile pour cette version de l'application
     * Cette fonction premet de créer et retourner
     * un role par rapport à une role de la base de donnée
     * dont l'id est passé en paramètre.
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param id : L'id du role qu'on veut récuperer de la base de donnée
     */
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

    /**
     * Permet d'insérer dans la base de donnée un role passée
     * en paramètre
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param role : AbstractRole - role qu'on veut insérer
     */
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

    /**
     * Permet de modifier dans la base de donnée un role passé
     * en paramètre
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param role : Role qu'on veut modifier
     */
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

    /**
     * Permet de supprimer dans la base de donnée un role dont
     * l'id est passé en paramètre
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param id : ID du role qu'on veut supprimer
     */
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

    /**
     * Retourne la liste de tous les roles présents dans
     * la base de données
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
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

    /**
     * Retourne le role dont l'id est passé en paramètre
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
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

    /**
     * Fonction non nécessaire et non implémentée
     * dans cette version de l'application
     * mais pourra par exemple trouver son utilité pour une fonction de recherche
     * dans une future version
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public List<AbstractRole> getRoleByName(String name) {
        return null;
    }
}
