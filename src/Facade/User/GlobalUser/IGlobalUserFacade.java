package Facade.User.GlobalUser;

import BuisnessLogic.User.AbstractUser;

import java.util.List;

public interface IGlobalUserFacade {
    boolean addUser(AbstractUser user);

    boolean modifyUser(AbstractUser user);

    boolean deleteUser(AbstractUser user);

    //List<AbstractUser> getUserByName(String name);

    boolean getAllUsers();

    AbstractUser getUserById(int id);

    List<AbstractUser> getListUsers();
}
