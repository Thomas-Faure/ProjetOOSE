package Facade.User.GlobalUser;

import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.User;
import DAO.MySQLDAOFactory;
import DAO.User.GlobalUser.GlobalUserDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Classe correspond à la façade qui gère les utilisateurs(GlobalUser).
 * Il aura une instance statique à partir de laquelle on pourra récupérer
 * les utilisateurs présents dans l'application. Est en lien avec le DAO GlobalUserDAO
 * correspondant pour récupérer les données depuis la base.
 */
public class GlobalUserFacade implements IGlobalUserFacade {
    private List<AbstractUser> users;
    private GlobalUserDAO daoFactory;

    public static GlobalUserFacade instance;

    private GlobalUserFacade(){
        daoFactory = MySQLDAOFactory.getUserDAO();
        this.users = new ArrayList<>();
    }

    public static GlobalUserFacade getInstance(){
        if(instance == null){
            instance = new GlobalUserFacade();
        }
        return instance;
    }

    public List<AbstractUser> getListIUsers(){
        return this.users;
    }

    public GlobalUserDAO getDao(){
        return this.daoFactory;
    }


    public boolean addUser(AbstractUser user) {
        if(instance.getDao().save((User) user)){
            //on ajouter la nouvelle idee à la liste
            instance.users.add(user);
            return true;
        }else {
            return false;
        }
    }

    public boolean modifyUser(AbstractUser user) {
        if(daoFactory.update(user)){
            //System.out.println(users.size());
            if(users.size() == 0){
                //this.users.add(user);
                return true;
            }

            else {
                int i = 0;
                //System.out.println(users + " " + users.size());
                while(this.users.get(i).getId() != user.getId()){
                    i++;
                }
                this.users.set(i, user);
                return true;
            }

        }
        else {
            return false;
        }
    }

    public boolean deleteUser(AbstractUser user){
        //System.out.println(users);
        if(daoFactory.delete(user.getId())){
            return true;
        }
        else {
            System.out.println("user non delete");
            return false;
        }
    }


    /*public List<AbstractIdea> getIdeaByName(String name) {
        return null;
    }*/

    public boolean getAllUsers() {
        this.users = daoFactory.getAllUsers();
        return true;
    }

    public AbstractUser getUserById(int id) {
        AbstractUser user;
        user = daoFactory.getUserById(id);
        return user;
    }

    @Override
    public List<AbstractUser> getListUsers() {
        return this.users;
    }
}
