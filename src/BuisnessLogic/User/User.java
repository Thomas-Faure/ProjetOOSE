package BuisnessLogic.User;

import Facade.IRoleFacade;

public class User extends AbstractUser {
    private Integer id;
    private String nom;
    private String username;
    private String prenom;
    private String ville;
    private String password;
    private String rue;
    private String cp;
    private String tel;
    private String email;
    private IRoleFacade companyRole;

    public User(int id, String username, String firstname, String lastname, String password) {
        this.id=id;
        this.username=username;
        this.prenom=firstname;
        this.nom=lastname;
        this.password=password;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUsername(){return this.username;}
    public String getVille() {
        return ville;
    }

    public String getRue() {
        return rue;
    }

    public String getCp() {
        return cp;
    }

    public String getPassword(){return password;}
    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public IRoleFacade getCompanyRole() {
        return companyRole;
    }
}
