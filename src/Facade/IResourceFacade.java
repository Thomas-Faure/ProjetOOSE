package Facade;

import BuisnessLogic.Ressource.AbstractResource;
import BuisnessLogic.Ressource.Resource;

import java.util.List;

public interface IResourceFacade {

    boolean addResource(AbstractResource resource);
    List<AbstractResource> getListResourceByProject(int projectID);
}
