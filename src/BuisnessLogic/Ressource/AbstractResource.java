package BuisnessLogic.Ressource;

public abstract class AbstractResource {
    int resourceID;
    String path;
    String filename;


    public abstract int getResourceID();

    public abstract void setResourceID(int resourceID);

    public abstract String getPath();

    public abstract void setPath(String path);

    public abstract String getFilename();

    public abstract void setFilename(String filename);

}
