package BuisnessLogic.User;

public class User extends AbstractUser {
    private int id;
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private String city = "Not entered yet";
    private String phoneNumber = "Not entered yet";
    private String email = "Not entered yet";
    private String position = "Not entered yet";
    private boolean isAdmin = false;

    public User(int newId, String newUsername, String newFirstname, String newLastname, String newPassword) {
        this.id = newId;
        this.username = newUsername;
        this.firstName = newFirstname;
        this.lastName = newLastname;
        this.password = newPassword;
    }

    public User(){

    }

    public int getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getLastName(){
        return lastName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getCity(){
        return city;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getEmail(){
        return email;
    }
    public String getPosition(){
        return position;
    }
    public boolean isAdmin() { return isAdmin;}

    public void setId(int newId){
        this.id = newId;
    }
    public void setUsername(String newUsername){
        this.username = newUsername;
    }
    public void setPassword(String newPassword){
        this.password = newPassword;
    }
    public void setLastName(String newLastName){
        this.lastName = newLastName;
    }
    public void setFirstName(String newFirstName){
        this.firstName = newFirstName;
    }
    public void setCity(String newCity){
        this.city = newCity;
    }
    public void setPhoneNumber(String newPhoneNumber){
        this.phoneNumber = newPhoneNumber;
    }
    public void setEmail(String newEmail){
        this.email = newEmail;
    }
    public void setPosition(String newPosition){
        this.position = newPosition;
    }
    public void setAdmin (boolean bool) { this.isAdmin = bool;}
}
