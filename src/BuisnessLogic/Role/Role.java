package BuisnessLogic.Role;

public class Role extends AbstractRole{

    private String roleName;
    private int idRole;

    public Role (int newid, String newname){
        this.idRole = newid;
        this.roleName = newname;
    }
    public String getName() {
        return roleName;
    }
    public int getId(){
        return this.idRole;
    }

    public void setName(String newRole) {
        this.roleName = newRole;
    }
    public void setId(int newId){
        this.idRole = newId;
    }
}
