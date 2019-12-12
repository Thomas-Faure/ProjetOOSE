package login;

import DAO.AbstractDAOFactory;

import DAO.MySQLDAOFactory;
import DAO.UserDAO;
import User.User;

public class SessionFacade implements ISessionFacade {
	UserDAO dao;
	User user;
	public SessionFacade() {
		dao = MySQLDAOFactory.getUserDAO();
		
	}
	public boolean login(String username,String password) {
		if(user == null) {
			this.user = dao.createUser(username, password);
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
