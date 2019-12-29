package Facade;

import BuisnessLogic.Ressource.AbstractResource;
import BuisnessLogic.Ressource.Resource;

public interface IResourceFacade {

    boolean addResource(AbstractResource resource);
    boolean getListResourceByProject(int projectID);
}
