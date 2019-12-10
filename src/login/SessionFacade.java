package login;

import DAO.AbstractDAOFactory;

import DAO.MySQLDAOFactory;
import DAO.UserDAO;
import user.User;

public class SessionFacade implements ISessionFacade {
	AbstractDAOFactory factory;
	UserDAO daoMySQL;
	User user;
	public SessionFacade() {
		factory = new MySQLDAOFactory();
		daoMySQL = factory.createUserDAO();
	}
	public boolean login(String username,String password) {
		if(user == null) {
			this.user = daoMySQL.createUser(username, password);
			if(this.user != null) {
				System.out.println("nous avons un nouvel utilisateur connecté :"+user.getName());
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	

	

}
