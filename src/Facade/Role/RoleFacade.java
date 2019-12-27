package Facade.Role;

import BuisnessLogic.Role.AbstractRole;
import DAO.AbstractDAOFactory;

public class RoleFacade implements IRoleFacade {
    private AbstractDAOFactory daoFactory;
    private AbstractRole role;
}
