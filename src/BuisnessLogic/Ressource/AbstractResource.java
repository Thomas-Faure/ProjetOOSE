package BuisnessLogic.Ressource;

/**
 * AbstractResource
 * @author Guillaume Tessier
 */
public abstract class AbstractResource {

    /**
     *
     * @return the resource id
     */
    public abstract int getResourceID();

    /**
     * Set the resource id
     * @param resourceID
     */
    public abstract void setResourceID(int resourceID);

    /**
     *
     * @return the path of a resource
     */
    public abstract String getPath();

    /**
     *Set the resource path
     * @param path
     */
    public abstract void setPath(String path);

    /**
     *
     * @return the filename of a resource
     */
    public abstract String getFilename();

    /**
     *
     * @param filename
     */
    public abstract void setFilename(String filename);

    /**
     *
     * @return a project id
     */
    public abstract int getIdProject();

}
