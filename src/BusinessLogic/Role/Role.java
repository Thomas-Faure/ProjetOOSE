package BusinessLogic.Role;

/**
 *
 * Cette Classe correspond aux roles de projet qui
 * existeront dans notre application. Les membres n'auront plus qu'à
 * être associés à 1 role parmis ceux existant
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
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
