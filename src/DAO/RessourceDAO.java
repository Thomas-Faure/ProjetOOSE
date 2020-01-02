package DAO;

import BuisnessLogic.Ressource.AbstractResource;

import java.util.List;

public interface RessourceDAO {
    boolean save(AbstractResource resource, int projectID);
    boolean update(AbstractResource resource);
    boolean delete(int resourceID);
    AbstractResource getResourceById(int resourceID);
    List<AbstractResource> getAllResourceByProject(int projectID);
}
