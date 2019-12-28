package Facade;

import BuisnessLogic.Ressource.Resource;

public interface IResourceFacade {

    boolean addResource(Resource resource);
    boolean getListResourceByProject(int projectID);
}
