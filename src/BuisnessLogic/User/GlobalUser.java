package BuisnessLogic.User;

public class GlobalUser extends User{

    public GlobalUser(int newId, String newUsername, String newFirstname, String newLastname, String newPassword) {
        super(newId, newUsername, newFirstname, newLastname, newPassword);
    }

    public GlobalUser (int newId, String newUsername, String newPassword, String newFirstname, String newLastname, String newCity,
                 String newphoneNumber, String newEmail, String newPosition, boolean newisAdmin){
        super(newId, newUsername, newPassword, newFirstname, newLastname, newCity, newphoneNumber, newEmail, newPosition, newisAdmin );
    }

    public GlobalUser() {

    }
}
