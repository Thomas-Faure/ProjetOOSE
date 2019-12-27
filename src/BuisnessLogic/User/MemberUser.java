package BuisnessLogic.User;


import BuisnessLogic.Role.AbstractRole;

public class MemberUser extends User {

    private AbstractRole memberRole;

    public MemberUser(int newId, String newUsername, String newFirstname, String newLastname, String newPassword) {
        super(newId, newUsername, newFirstname, newLastname, newPassword);
    }

    public AbstractRole getRole (){
        return memberRole;
    }

    public void setRole (AbstractRole newRole){
        this.memberRole = newRole;
    }


}
