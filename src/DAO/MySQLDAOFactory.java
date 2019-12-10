package DAO;

public  class MySQLDAOFactory extends AbstractDAOFactory {

	//mettre ici la connexion JDBC
	@Override
	public UserDAO createUserDAO() {
		return new UserDAOMySQL();
	}



	
	
	

}
