package DAO.Role;

import BuisnessLogic.Role.AbstractRole;
import BuisnessLogic.Role.Role;

import java.util.List;

public interface RoleDAO {

    public Role createRoleById(int id);
    boolean save(AbstractRole role);
    boolean update(AbstractRole role);
    boolean delete(int id);
    //public Role createRole(String name);
    List<AbstractRole> getAllRoles();
    AbstractRole getRoleById(int id);
}
