package Facade;

import BuisnessLogic.Ressource.*;
import DAO.AbstractDAOFactory;
import DAO.MySQLDAOFactory;
import DAO.RessourceDAO;

import java.util.ArrayList;
import java.util.List;

public class ResourceFacade implements IResourceFacade {
    private List<AbstractResource> resources;
    private RessourceDAO dao;

    public static ResourceFacade FacadeInstance;
    private ResourceFacade(){
        this.dao = MySQLDAOFactory.getResourceDAO();
        this.resources = new ArrayList<>();
    }

    public static ResourceFacade getInstance(){
        if(FacadeInstance == null){
            FacadeInstance = new ResourceFacade();
        }
        return FacadeInstance;
    }

    public RessourceDAO getDao(){
        return this.dao;
    }
    public List<AbstractResource> getListResource(){
        return this.resources;
    }

    @Override
    public boolean addResource(AbstractResource resource) {
        if(FacadeInstance.getDao().save(resource)){
            FacadeInstance.getListResource().add(resource);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean getListResourceByProject(int projectID) {
        return false;
    }
}
