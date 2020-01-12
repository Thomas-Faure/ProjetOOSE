package BuisnessLogic.Ressource;

/**
 *
 * @author Guillaume Tessier
 */
public class Resource extends AbstractResource {

    int resourceID;
    String path;
    String filename;
    int idProject;


    /**
     * Constructor with 3 parameters
     * @param path
     * @param filename
     * @param idProject
     */
    public Resource(String path, String filename, int idProject) {
        this.path = path;
        this.filename = filename;
        this.idProject = idProject;
    }

    /**
     *Constructor with 4 parameters
     * @param resourceID
     * @param path
     * @param filename
     * @param idProject
     */
    public Resource(int resourceID, String path, String filename, int idProject) {
        this.resourceID = resourceID;
        this.path = path;
        this.filename = filename;
        this.idProject = idProject;
    }

    @Override
    public int getResourceID() {
        return resourceID;
    }

    @Override
    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getFilename() {
        return filename;
    }

    @Override
    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public int getIdProject() {
        return idProject;
    }


}
