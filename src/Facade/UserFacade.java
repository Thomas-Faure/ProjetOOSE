package Facade;

import BuisnessLogic.User.AbstractUser;
import DAO.AbstractDAOFactory;

public class UserFacade implements IUserFacade {
    private AbstractUser[] users;
    private AbstractDAOFactory daoFactory;
}
