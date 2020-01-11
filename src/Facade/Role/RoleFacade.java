package Facade.Role;

import BuisnessLogic.Role.AbstractRole;
import DAO.MySQLDAOFactory;
import DAO.Role.RoleDAO;

import java.util.ArrayList;
import java.util.List;

public class RoleFacade implements IRoleFacade {
    private List<AbstractRole> roles;
    private RoleDAO daoFactory;

    public static RoleFacade instance;

    private RoleFacade(){
        daoFactory = MySQLDAOFactory.getInstance().getRoleDAO();
        this.roles = new ArrayList<>();
    }

    public static RoleFacade getInstance(){
        if(instance == null){
            instance = new RoleFacade();
        }
        return instance;
    }

    public List<AbstractRole> getListRoles(){
        return this.roles;
    }

    public RoleDAO getDao(){
        return this.daoFactory;
    }


    public boolean addRole(AbstractRole role) {
        if(instance.getDao().save(role)){
            //on ajouter le nouveau role à la liste
            instance.roles.add(role);
            return true;
        }else {
            return false;
        }
    }

    public boolean modifyRole(AbstractRole role) {
        if(daoFactory.update(role)){
            this.roles.set(role.getId(), role);
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteRole(AbstractRole role){
        if(daoFactory.delete(role.getId())){
            return true;
        }
        else {
            return false;
        }
    }

    /*public List<AbstractIdea> getIdeaByName(String name) {
        return null;
    }*/

    public boolean getAllRoles() {
        this.roles = daoFactory.getAllRoles();
        return true;
    }

    public AbstractRole getRoleById(int id) {
        AbstractRole role;
        role = daoFactory.getRoleById(id);
        return role;
    }
}
