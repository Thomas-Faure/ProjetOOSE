package Facade.Role;

import BuisnessLogic.Role.AbstractRole;

public interface IRoleFacade {
    boolean addRole(AbstractRole idea);

    boolean modifyRole(AbstractRole idea);

    boolean deleteRole(AbstractRole idea);

    //List<AbstractIdea> getIdeaByName(String name);

    boolean getAllRoles();

    AbstractRole getRoleById(int id);
}
