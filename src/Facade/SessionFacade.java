package Facade;

import BusinessLogic.User.User;
import DAO.MySQLDAOFactory;
import DAO.Task.TaskDAO;
import DAO.User.GlobalUser.GlobalUserDAO;


/**
 * It's the facade to manage the user connection, contain the user if he is logged
 */
public class SessionFacade implements ISessionFacade {
	GlobalUserDAO userDAO;
	TaskDAO taskDAO;
	User user;

	public static ISessionFacade instance;

	private SessionFacade() {
		userDAO = MySQLDAOFactory.getInstance().getUserDAO();
		taskDAO = MySQLDAOFactory.getInstance().getTaskDAO();
		
	}

	/**Method to return the instance of session facade
	 * @return
	 */
	public static SessionFacade getInstance(){
		if(instance==null){
			instance = new SessionFacade();
		}
		return (SessionFacade)instance;
	}

	/**
	 * Method to remove the user from the SessionFacade, this method is called if the user want to log off
	 */
	public void removeUser(){
		this.user=null;
	}

	/**Method to get the current logged user
	 * @return
	 */
	public User getUser(){
		return user;
	}


	/**Method to set the SessionFacade user
	 * @param user
	 */
	public void setUser(User user){
		this.user = user;
	};


	/**Method to try to login , if it's true call setUser() and return true else return false
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username,String password) {
		if(user == null) {
			setUser(userDAO.createUser(username, password));
			if(this.user != null) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	

	

}
