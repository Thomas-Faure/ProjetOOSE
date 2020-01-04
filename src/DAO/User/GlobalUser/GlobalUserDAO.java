package DAO.User.GlobalUser;

import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.Member;
import BuisnessLogic.User.User;

import java.util.List;

public interface GlobalUserDAO {
    public User createUser(String identifiant, String username);
    //public User createUserById(int id);
    public boolean save(AbstractUser user);
    public boolean update(AbstractUser user);
    public boolean delete(int id);
    List<AbstractUser> getAllUsers();
    AbstractUser getUserById(int id);
    List<Member> getUserByName(String name);
}
