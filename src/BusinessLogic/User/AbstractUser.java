package BusinessLogic.User;

/**
 *
 * Cette Interface correspond aux ensembles des différents types
 * de User  dans notre application. Pour l'instant il existe les "member"
 * qui correspondent aux membres associés à un projet; et les "globalUser"
 * qui correspondent à l'ensemble des utilisateurs de l'application.
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public abstract class AbstractUser {

    public abstract int getId();
    public abstract String getUsername();
    public abstract String getPassword();
    public abstract String getLastName();
    public abstract String getFirstName();
    public abstract String getCity();
    public abstract String getPhoneNumber();
    public abstract String getEmail();
    public abstract String getPosition();
    public abstract boolean isAdmin();

    public abstract void setId(int newId);
    public abstract void setUsername(String newUsername);
    public abstract void setPassword(String newPassword);
    public abstract void setLastName(String newLastName);
    public abstract void setFirstName(String newFirstName);
    public abstract void setCity(String newCity);
    public abstract void setPhoneNumber(String newPhoneNumber);
    public abstract void setEmail(String newEmail);
    public abstract void setPosition(String newPosition);
    public abstract void setAdmin (boolean bool);
}
