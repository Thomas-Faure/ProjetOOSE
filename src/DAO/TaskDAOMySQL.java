package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import Task.Task;
import User.User;

public class TaskDAOMySQL implements TaskDAO{

	protected Connection connect = null;
    private static final String INSERT = "INSERT INTO task (username, firstname, lastname, password) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE task SET username=?, firstName=?, lastName=?, password=? WHERE id=?";
    private static final String DELETE = "DELETE FROM task WHERE id=?";
	
	public TaskDAOMySQL() {
		this.connect=MySQLConnector.getInstance().getConnection();
	}
	@Override
	public Task createTaskById(int id) {
		 Task task=null;
		    try {
		    String query = "SELECT * FROM task WHERE id="+id;
		      ResultSet result = this.connect.createStatement(
			      ResultSet.TYPE_SCROLL_INSENSITIVE,
			      ResultSet.CONCUR_READ_ONLY).executeQuery(query);
		      if(result.first()) {
		    		  System.out.println("correct");
		    		  //à changer
		    		  task= new Task(    
		    				  result.getInt("id"),
		    		          result.getString("username"),
		    		          result.getInt("test"),
		    		          result.getDate("deadline").toLocalDate(),
		    		          new User()); 
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return task;
	}

	@Override
	public boolean save(Task task) {
		try {
            PreparedStatement ps = connect.prepareStatement(INSERT);
            ps.setString(1, task.getName());
            ps.setInt(2, task.getPriority());
            ps.setDate(3, java.sql.Date.valueOf(task.getDeadline()));
            ps.setInt(4, task.getCreator().getId());
            ps.executeUpdate();
            ps.close();
 
            System.out.println("Nouvelle task dans la base: " + task.toString());
            return true;
        } catch (SQLException e) {
            
        	System.out.println(e);
            return false;
        }
		
	}

	@Override
	public boolean update(Task task) {
		try {
			 
            PreparedStatement ps = connect.prepareStatement(UPDATE);
            ps.setString(1, task.getName());
            ps.setInt(2, task.getPriority());
            ps.setDate(3, java.sql.Date.valueOf( task.getDeadline() ));
            ps.setInt(4, task.getCreator().getId());
            ps.setInt(5, task.getId());
             
            ps.executeUpdate();
            ps.close();
 
            System.out.println("La task " + task.getId() + " contient maintenant: " + task.toString());
 
            return true;
        } catch (SQLException e) {
           
            System.out.println(e);
            return false;
        }
	}

	@Override
	public void delete(int id) {
		 
        try {
 
            PreparedStatement ps = connect.prepareStatement(DELETE);
 
            ps.setInt(1, id);
 
            ps.executeUpdate();
            ps.close();
 
            System.out.println("Task with id: " + id + " was sucesfully deleted from DB.");
 
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
		
	}

}
