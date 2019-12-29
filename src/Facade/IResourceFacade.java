package Facade;

import BuisnessLogic.Ressource.AbstractResource;
import BuisnessLogic.Ressource.Resource;

import java.util.List;

public interface IResourceFacade {

    boolean addResource(AbstractResource resource);
    boolean deleteResource(int resourceID);
    AbstractResource getResourceById(int resourceID);
    List<AbstractResource> getListResourceByProject(int projectID);

}
