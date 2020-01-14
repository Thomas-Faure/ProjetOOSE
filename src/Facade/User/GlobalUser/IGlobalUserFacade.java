package Facade.User.GlobalUser;

import BusinessLogic.User.AbstractUser;

import java.util.List;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * Cette Interface correspond à la façade qui gère les utilisateurs(GlobalUser).
 * Il aura une instance statique à partir de laquelle on pourra récupérer
 * les utilisateurs présents dans l'application. Est en lien avec le DAO GlobalUserDAO
 * correspondant pour récupérer les données depuis la base.
 */
public interface IGlobalUserFacade {
    boolean addUser(AbstractUser user);

    boolean modifyUser(AbstractUser user);

    boolean deleteUser(AbstractUser user);

    //List<AbstractUser> getUserByName(String name);

    boolean getAllUsers();

    AbstractUser getUserById(int id);

    List<AbstractUser> getListUsers();
}
