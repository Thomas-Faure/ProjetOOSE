package Facade.Resource;

import BusinessLogic.Ressource.*;
import DAO.MySQLDAOFactory;
import DAO.Resource.IResourceDAO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public class ResourceFacade implements IResourceFacade {
    private List<AbstractResource> resources;
    private IResourceDAO dao;

    public static ResourceFacade FacadeInstance;
    private ResourceFacade(){
        this.dao = MySQLDAOFactory.getInstance().getResourceDAO();
        this.resources = new ArrayList<>();
    }

    public static ResourceFacade getInstance(){
        if(FacadeInstance == null){
            FacadeInstance = new ResourceFacade();
        }
        return FacadeInstance;
    }

    public IResourceDAO getDao(){
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
    public boolean deleteResource(int resourceID) {
        return FacadeInstance.getDao().delete(resourceID);
    }

    @Override
    public AbstractResource getResourceById(int resourceID) {
        return FacadeInstance.getDao().getResourceById(resourceID);
    }

    @Override
    public List<AbstractResource> getListResourceByProject(int projectID) {
        return FacadeInstance.getDao().getAllResourceByProject(projectID);
    }
}
