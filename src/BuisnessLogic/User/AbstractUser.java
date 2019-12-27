package BuisnessLogic.User;

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

    public abstract void setId(int newId);
    public abstract void setUsername(String newUsername);
    public abstract void setPassword(String newPassword);
    public abstract void setLastName(String newLastName);
    public abstract void setFirstName(String newFirstName);
    public abstract void setCity(String newCity);
    public abstract void setPhoneNumber(String newPhoneNumber);
    public abstract void setEmail(String newEmail);
    public abstract void setPosition(String newPosition);
}
