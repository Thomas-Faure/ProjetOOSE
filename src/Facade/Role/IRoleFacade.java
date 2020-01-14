package Facade.Role;

import BusinessLogic.Role.AbstractRole;

import java.util.List;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Interface correspond à la façade qui gère les roles.
 * Il aura une instance statique à partir de laquelle on pourra récupérer
 * les roles présents dans l'application. Est en lien avec le DAO RoleDAO
 * correspondant pour récupérer les données depuis la base.
 */
public interface IRoleFacade {
    boolean addRole(AbstractRole idea);

    boolean modifyRole(AbstractRole idea);

    boolean deleteRole(AbstractRole idea);

    //List<AbstractIdea> getIdeaByName(String name);

    boolean getAllRoles();

    public List<AbstractRole> getListRoles();

    AbstractRole getRoleById(int id);
}
