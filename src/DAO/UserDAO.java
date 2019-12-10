package DAO;

import user.User;

public interface UserDAO{
	public User createUser(String identifiant,String username);
	public boolean save();
	public boolean update();
}
