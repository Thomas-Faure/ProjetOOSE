package DAO;

import User.User;

public interface UserDAO{
	public User createUser(String identifiant,String username);
	public boolean save(User user);
	public boolean update(User user);
	void delete(int id);
}
