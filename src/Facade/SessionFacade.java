package Facade;

import BuisnessLogic.User.User;
import DAO.MySQLDAOFactory;
import DAO.TaskDAO;
import DAO.User.UserDAO;

public class SessionFacade implements ISessionFacade {
	UserDAO userDAO;
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
	public User getUser(){
		return user;
	}

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
