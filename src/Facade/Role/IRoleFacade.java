package Facade.Role;

import BuisnessLogic.Role.AbstractRole;

import java.util.List;

public interface IRoleFacade {
    boolean addRole(AbstractRole idea);

    boolean modifyRole(AbstractRole idea);

    boolean deleteRole(AbstractRole idea);

    //List<AbstractIdea> getIdeaByName(String name);

    boolean getAllRoles();

    public List<AbstractRole> getListRoles();

    AbstractRole getRoleById(int id);
}
