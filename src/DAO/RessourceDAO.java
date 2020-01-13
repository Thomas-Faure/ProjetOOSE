package DAO;

import BusinessLogic.Ressource.AbstractResource;

import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public interface RessourceDAO {

    /**
     * Insert a new resource in the database
     * @param resource
     * @return true or false, depend if the action succeed
     */
    boolean save(AbstractResource resource);

    /**
     * Update a resource in the database
     * @param resource
     * @return true or false, depend if the action succeed
     */
    boolean update(AbstractResource resource);

    /**
     * Delete a new resource in the database
     * @param resourceID
     * @return true or false, depend if the action succeed
     */
    boolean delete(int resourceID);

    /**
     * Found a resource in the database and return them
     * @param resourceID
     * @return a AbstractResource
     */
    AbstractResource getResourceById(int resourceID);

    /**
     * Found all resources relatives to a project in the database and return all of them in a list
     * @param projectID
     * @return a list of AbstractResource
     */
    List<AbstractResource> getAllResourceByProject(int projectID);
}
