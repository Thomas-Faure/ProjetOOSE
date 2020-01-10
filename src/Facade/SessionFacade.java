package Facade;

import BuisnessLogic.User.User;
import DAO.MySQLDAOFactory;
import DAO.Task.TaskDAO;
import DAO.User.GlobalUser.GlobalUserDAO;

public class SessionFacade implements ISessionFacade {
	GlobalUserDAO userDAO;
	TaskDAO taskDAO;
	User user;

	public static ISessionFacade instance;

	private SessionFacade() {
		userDAO = MySQLDAOFactory.getUserDAO();
		taskDAO = MySQLDAOFactory.getTaskDAO();
		
	}

	public static SessionFacade getInstance(){
		if(instance==null){
			instance = new SessionFacade();
		}
		return (SessionFacade)instance;
	}
	public void removeUser(){
		this.user=null;
	}
	public User getUser(){
		return user;
	}

	public void setUser(User user){
		this.user = user;
	};

	public boolean login(String username,String password) {
		if(user == null) {
			this.user = userDAO.createUser(username, password);
			if(this.user != null) {
				System.out.println("nous avons un nouvel utilisateur connecté :"+user.getUsername());
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	

	

}
