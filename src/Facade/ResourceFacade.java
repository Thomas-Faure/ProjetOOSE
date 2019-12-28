package Facade;

import BuisnessLogic.Ressource.*;
import DAO.AbstractDAOFactory;
import DAO.MySQLDAOFactory;

import java.util.ArrayList;
import java.util.List;

public class ResourceFacade implements IResourceFacade {
    private List<AbstractResource> resources;
    private AbstractDAOFactory daoFactory;

    public static ResourceFacade FacadeInstance;
    private ResourceFacade(){
        //daoFactory = MySQLDAOFactory.getResourceDAO();
        this.resources = new ArrayList<>();
    }

    public static ResourceFacade getInstance(){
        if(FacadeInstance == null){
            FacadeInstance = new ResourceFacade();
        }
        return FacadeInstance;
    }

    public AbstractDAOFactory getDao(){
        return this.daoFactory;
    }
    public List<AbstractResource> getListResource(){
        return this.resources;
    }

    /*public boolean addResource(Resource resource){
         if(FacadeInstance.getDao().save(resource)){
             FacadeInstance.getListResource().add(resource);
            return true;
        }else {
            return false;
        }
    }*/
}
