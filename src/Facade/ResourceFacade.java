package Facade;

import BuisnessLogic.Ressource.AbstractResource;
import DAO.AbstractDAOFactory;

public class ResourceFacade implements IResourceFacade {
    private AbstractResource[] resources;
    private AbstractDAOFactory daoFactory;
}
