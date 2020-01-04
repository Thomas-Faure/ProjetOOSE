package BuisnessLogic.User;


import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Role.AbstractRole;

public class Member extends User {

    private AbstractRole memberRole;
    private AbstractProject project;

    public Member(int newId, String newUsername, String newFirstname, String newLastname, String newPassword) {
        super(newId, newUsername, newFirstname, newLastname, newPassword);
    }
    public Member (int newId, String newUsername, String newPassword, String newFirstname, String newLastname, String newCity,
                       String newphoneNumber, String newEmail, String newPosition, boolean newisAdmin){
        super(newId, newUsername, newPassword, newFirstname, newLastname, newCity, newphoneNumber, newEmail, newPosition, newisAdmin );
    }

    public Member(){

    }

    public AbstractRole getRole (){
        return memberRole;
    }

    public void setRole (AbstractRole newRole){
        this.memberRole = newRole;
    }

    public AbstractProject getProject (){
        return project;
    }

    public void setProject (AbstractProject newProject){
        this.project = newProject;
    }


}
