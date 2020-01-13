package DAO.Project;

import BusinessLogic.Project.AbstractProject;
import BusinessLogic.Project.Project;
import DAO.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAOMySQL implements ProjectDAO {

    private static final String INSERT = "INSERT INTO project (title, description, dateCreation, isAgile) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE project SET title=?, description=? WHERE idProject=?";
    private static final String DELETE = "DELETE FROM project WHERE idProject=?";
    private static final String ALL = "SELECT * from project";
    private static final String PROJECTBYID = "SELECT * from project where idProject=?";

    @Override
    public List<AbstractProject> getAllProjects() {
        List<AbstractProject> list = new ArrayList<>();
        try {

            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALL);


            ResultSet rs = ps.executeQuery();
            while(rs.next()){


                Project project = new Project(
                        rs.getInt("idProject"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("dateCreation").toLocalDate(),
                        rs.getBoolean("isAgile")
                );

                list.add(project);
            }
            ps.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(DELETE);
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            ps.close();
            if (i > 0) {
                return true;
            } else {

                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(AbstractProject project) {
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.setDate(3, java.sql.Date.valueOf(project.getDateCreation().plusDays(1)));
            ps.setBoolean(4, project.isAgile());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(AbstractProject project) {
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(UPDATE);
            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.setInt(3, project.getId());
            int i = ps.executeUpdate();
            ps.close();
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public AbstractProject getProjectById(int id) {
        AbstractProject project = null;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(PROJECTBYID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                project = new Project(
                        rs.getInt("idProject"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("dateCreation").toLocalDate(),
                        rs.getBoolean("isAgile")
                );

            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return project;
    }
}
