package Facade.User;

public class UserFacade implements IUserFacade {
  /*  private List<AbstractUser> users;
    private UserDAO daoFactory;

    public static UserFacade instance;

    private UserFacade(){
        daoFactory = MySQLDAOFactory.getUserDAO();
        this.users = new ArrayList<>();
    }

    public static UserFacade getInstance(){
        if(instance == null){
            instance = new UserFacade();
        }
        return instance;
    }

    public List<AbstractUser> getListIUsers(){
        return this.users;
    }

    public UserDAO getDao(){
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
        if(daoFactory.update((User) user)){
            this.users.set(user.getId(), user);
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteUser(AbstractUser user){
        if(daoFactory.delete(user.getId())){
            return true;
        }
        else {
            return false;
        }
    }


    /*public List<AbstractIdea> getIdeaByName(String name) {
        return null;
    }*/
/*
    public boolean getAllUsers() {
        this.users = daoFactory.getAllUsers();
        return true;
    }

    public AbstractUser getUserById(int id) {
        AbstractUser user;
        user = daoFactory.getUserById(id);
        return user;
    }
    */
}
