package DAO.Task;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;
import BuisnessLogic.Task.TaskState;
import DAO.MySQLConnector;
import Facade.Project.ProjectFacade;
import Facade.User.GlobalUser.GlobalUserFacade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOMySQL implements TaskDAO {

    private static final String INSERT = "INSERT INTO task (name, priority, deadline, creator,description,state,idProject,idSprint) VALUES (?, ?, ?, ?,?,?,?,?)";
    private static final String UPDATE = "UPDATE task SET name=?, priority=?, deadline=?, creator=?, description=?, state=?, idSprint=? WHERE idTask=?";
    private static final String DELETE = "DELETE FROM task WHERE idTask=?";
    private static final String ALL = "SELECT * from task";
    private static final String ALLBYPROJ = "SELECT * from task where idProject=?";
    private static final String ALLBYPROJBACKLOG = "SELECT * from task where idProject=? and idSprint is null";

    private static final String ALLBYSPRINT = "SELECT * from task where idSprint=?";
    private static final String TASKBYID = "SELECT * from task where idTask=?";
    private static final String TASKBYNAME = "SELECT * from task where name=?";
	
	public TaskDAOMySQL() {
		
	}


	@Override
	public boolean save(AbstractTask task) {
		try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
            ps.setString(1, task.getName());
            ps.setInt(2, task.getPriority());
            ps.setDate(3, java.sql.Date.valueOf(task.getDeadline()));
            ps.setInt(4, task.getCreator().getId());
            ps.setString(5, task.getDescription());
            ps.setString(6, task.getState().getStatetoString());

            ps.setInt(7, task.getProject().getId());
            if(task.getIdSprint() != null) {
                ps.setInt(8, task.getIdSprint());
            }else{
                ps.setNull(8, java.sql.Types.INTEGER);
            }
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
        	e.printStackTrace();
            return false;
        }
		
	}

	@Override
	public boolean update(AbstractTask task) {
		try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(UPDATE);
            ps.setString(1, task.getName());
            ps.setInt(2, task.getPriority());
            ps.setDate(3, java.sql.Date.valueOf( task.getDeadline() ));
            ps.setInt(4, task.getCreator().getId());
            ps.setString(5, task.getDescription());
            ps.setString(6, task.getStateString());
            if(task.getIdSprint() != null) {
                ps.setInt(7, task.getIdSprint());
            }else{
                ps.setNull(7, java.sql.Types.INTEGER);
            }
            ps.setInt(8, task.getId());
            int i = ps.executeUpdate();
            ps.close();
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
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
    public AbstractTask getTaskById(int id){
	    AbstractTask task = null;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(TASKBYID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                task = new Task(
                        rs.getInt("idTask"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("priority"),
                        rs.getDate("deadline").toLocalDate(),
                        GlobalUserFacade.getInstance().getUserById(rs.getInt("creator")),
                        TaskState.getStateByString(rs.getString("state")),
                        ProjectFacade.getInstance().getProjectById(rs.getInt("idProject")));
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	    return task;
    }

    @Override
    public List<AbstractTask> getTaskByName(String name) {
        List<AbstractTask> tasks = null;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(TASKBYNAME);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                tasks.add(new Task(
                        rs.getInt("idTask"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("priority"),
                        rs.getDate("deadline").toLocalDate(),
                        GlobalUserFacade.getInstance().getUserById(rs.getInt("creator")),
                        TaskState.getStateByString(rs.getString("state")),
                        ProjectFacade.getInstance().getProjectById(rs.getInt("idProject"))));
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    @Override
    public List<AbstractTask> getTasksFromSprintId(int id) {
        List<AbstractTask> list = new ArrayList<>();
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALLBYSPRINT);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                AbstractTask task = new Task(
                        rs.getInt("idTask"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("priority"),
                        rs.getDate("deadline").toLocalDate(),
                        GlobalUserFacade.getInstance().getUserById(rs.getInt("creator")),
                        TaskState.getStateByString(rs.getString("state")),ProjectFacade.getInstance().getListProjects().get(0),
                        rs.getInt("idSprint"));
                list.add(task);
            }
            ps.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return list;

    }

    @Override
    public List<AbstractTask> getAllTasks() {
	    List<AbstractTask> list = new ArrayList<>();
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                AbstractTask task = new Task(
                        rs.getInt("idTask"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("priority"),
                        rs.getDate("deadline").toLocalDate(),
                        GlobalUserFacade.getInstance().getUserById(rs.getInt("creator")),
                        TaskState.getStateByString(rs.getString("state")),
                        ProjectFacade.getInstance().getProjectById(rs.getInt("idProject")));
                list.add(task);
            }
            ps.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
    public List<AbstractTask> getAllBacklogTasks(AbstractProject project) {
        List<AbstractTask> list = new ArrayList<>();
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALLBYPROJBACKLOG);
            ps.setInt(1, project.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                AbstractTask task = new Task(
                        rs.getInt("idTask"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("priority"),
                        rs.getDate("deadline").toLocalDate(),
                        GlobalUserFacade.getInstance().getUserById(rs.getInt("creator")),
                        TaskState.getStateByString(rs.getString("state")),ProjectFacade.getInstance().getListProjects().get(0));
                list.add(task);
            }
            ps.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
    public List<AbstractTask> getAllTasks(AbstractProject project) {
        List<AbstractTask> list = new ArrayList<>();
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALLBYPROJ);
            ps.setInt(1, project.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                AbstractTask task = new Task(
                        rs.getInt("idTask"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("priority"),
                        rs.getDate("deadline").toLocalDate(),
                        GlobalUserFacade.getInstance().getUserById(rs.getInt("creator")),
                        TaskState.getStateByString(rs.getString("state")),ProjectFacade.getInstance().getListProjects().get(0));
                list.add(task);
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
