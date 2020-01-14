package BusinessLogic.Role;

/**
 *
 * Cette Interface correspond aux roles de projet qui
 * existeront dans notre application. Les membres n'auront plus qu'à
 * être associés à 1 role parmis ceux existant
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public abstract class AbstractRole {

    public abstract String getName();

    public abstract void setName(String newRole);

    public abstract int getId();

    public abstract void setId(int newid);
}
