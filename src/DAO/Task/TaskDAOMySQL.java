package DAO.Task;


import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;
import BuisnessLogic.Task.TaskState;
import BuisnessLogic.User.User;
import DAO.MySQLConnector;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TaskDAOMySQL implements TaskDAO {

	
    private static final String INSERT = "INSERT INTO task (name, priority, deadline, creator,description,state) VALUES (?, ?, ?, ?,?,?)";
    private static final String UPDATE = "UPDATE task SET name=?, priority=?, deadline=?, creator=?, description=?, state=? WHERE id=?";
    private static final String DELETE = "DELETE FROM task WHERE id=?";
    private static final String ALL = "SELECT * from task";
    private static final String TASKBYID = "SELECT * from task where id=?";
    private static final String TASKBYNAME = "SELECT * from task where name=?";
	
	public TaskDAOMySQL() {
		
	}
	@Override
	public Task createTaskById(int id) {
		 Task task=null;
		    try {
		    String query = "SELECT * FROM task WHERE id="+id;
		      ResultSet result = MySQLConnector.getSQLConnection().createStatement(
			      ResultSet.TYPE_SCROLL_INSENSITIVE,
			      ResultSet.CONCUR_READ_ONLY).executeQuery(query);
		      if(result.first()) {

		    		  //à changer
		    		  task= new Task(    
		    				  result.getInt("id"),
		    		          result.getString("name"),
                              result.getString("description"),
		    		          result.getInt("test"),
		    		          result.getDate("deadline").toLocalDate(),
		    		          new User(),
                              TaskState.getStateByString(result.getString("state")));
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return task;
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
            ps.executeUpdate();
            ps.close();
 

            return true;
        } catch (SQLException e) {
            
        	e.printStackTrace();
            return false;
        }
		
	}

    public static void main(String[] args) {
	    TaskDAOMySQL sql = new TaskDAOMySQL();

        AbstractTask tast = sql.getTaskById(2);

        tast.setId(3);

        sql.update((Task)tast);
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
            ps.setInt(7, task.getId());

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
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("priority"),
                        rs.getDate("deadline").toLocalDate(),
                        new User(3,"thomas","faure","faure","faure"),
                        TaskState.getStateByString(rs.getString("state")));
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
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("priority"),
                        rs.getDate("deadline").toLocalDate(),
                        new User(3,"thomas","faure","faure","faure"),
                        TaskState.getStateByString(rs.getString("state"))));
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }
    @Override
    public List<AbstractTask> getAllTasks() {
	    List<AbstractTask> list = new ArrayList<>();
        try {

            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALL);


            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                AbstractTask task = new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("priority"),
                        rs.getDate("deadline").toLocalDate(),
                        null,
                        TaskState.getStateByString(rs.getString("state")));
                list.add(task);
            }
            ps.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return list;
    }



}
