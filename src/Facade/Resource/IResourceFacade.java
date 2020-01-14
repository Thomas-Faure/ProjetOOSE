package Facade.Resource;

import BusinessLogic.Ressource.AbstractResource;

import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public interface IResourceFacade {

    /**
     * Access to the DAO to insert a new resource in the database
     * @param resource
     * @return true or false, depend if the action succeed
     */
    boolean addResource(AbstractResource resource);

    /**
     * Access to the DAO to delete a resource in the database
     * @param resourceID
     * @return true or false, depend if the action succeed
     */
    boolean deleteResource(int resourceID);

    /**
     * Access to the DAO to get a resource with the id
     * @param resourceID
     * @return a AbstractResource
     */
    AbstractResource getResourceById(int resourceID);

    /**
     * Access to the DAO to get all resources relative to the project
     * @param projectID
     * @return a list of AbstractResource
     */
    List<AbstractResource> getListResourceByProject(int projectID);

}
