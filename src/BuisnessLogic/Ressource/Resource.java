package BuisnessLogic.Ressource;

public class Resource extends AbstractResource {

    int resourceID;
    String path;
    String filename;

    public Resource(int resourceID, String path, String filename) {
        this.resourceID = resourceID;
        this.path = path;
        this.filename = filename;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


}
