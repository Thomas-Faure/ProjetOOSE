package DAO;


import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;
import BuisnessLogic.User.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class TaskDAOMySQL implements TaskDAO{

	
    private static final String INSERT = "INSERT INTO task (name, priority, deadline, creator,description) VALUES (?, ?, ?, ?,?)";
    private static final String UPDATE = "UPDATE task SET name=?, priority=?, deadline=?, creator=?, description=? WHERE id=?";
    private static final String DELETE = "DELETE FROM task WHERE id=?";
    private static final String ALL = "SELECT * from task";
    private static final String TASKBYID = "SELECT * from task where id=?";
	
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
		    		  System.out.println("correct");
		    		  //à changer
		    		  task= new Task(    
		    				  result.getInt("id"),
		    		          result.getString("name"),
                              result.getString("description"),
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
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
            ps.setString(1, task.getName());
            ps.setInt(2, task.getPriority());
            ps.setDate(3, java.sql.Date.valueOf(task.getDeadline()));
            ps.setInt(4, task.getCreator().getId());
            ps.setString(5, task.getDescription());
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
			 
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(UPDATE);
            ps.setString(1, task.getName());
            ps.setInt(2, task.getPriority());
            ps.setDate(3, java.sql.Date.valueOf( task.getDeadline() ));
            ps.setInt(4, task.getCreator().getId());
            ps.setString(5, task.getDescription());
            ps.setInt(6, task.getId());
             
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
	public boolean delete(int id) {
		 
        try {
 
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(DELETE);
 
            ps.setInt(1, id);
 
            ps.executeUpdate();
            ps.close();
 
            System.out.println("Task with id: " + id + " was sucesfully deleted from DB.");

 
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return true;



	}
	@Override
    public AbstractTask getTaskById(int id){
	    Task task = null;
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
                        new User(3,"thomas","faure","faure","faure"));
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	    return task;
    }

    @Override
    public List<AbstractTask> getAllTasks() {
	    List<AbstractTask> list = new ArrayList<>();
        try {

            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALL);


            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("priority"),
                        rs.getDate("deadline").toLocalDate(),
                        null);
                list.add(task);
            }
            ps.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return list;
    }



}
