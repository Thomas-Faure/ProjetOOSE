package BusinessLogic.Idea;

import BusinessLogic.User.AbstractUser;

/**
 * Cette Interface correspond aux idées que les utilisateurs
 * peuvent publier pour proposer des suggestions
 * @author Lauren Unquera - Polytech Montpellier IG4
 *
 */
public abstract class AbstractIdea {

    public abstract int getId();

    public abstract String getName();

    public abstract AbstractUser getCreator();

    public abstract String getDescription();

    public abstract String getSubject();

    public abstract String getState();


    public abstract void setId(int id);

    public abstract void setName(String name);

    public abstract void setCreator(AbstractUser creator);

    public abstract void setDescription(String description);

    public abstract void setSubject(String subject);

    /**
     * Simple accesseur mais privilégiez les fonctions refuse() et validate()
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param state Doit être soit "Validated", soit "Refused"
     *
     */
    public abstract void setState(String state);

    /**
     *
     * Pour l'instant ne fait rien de spécial autre que modifier l'état de
     * l'idée car on décide de supprimer les
     * idées qui sont "Refused", mais si l'on décide de garder un historique pour une future
     * version de l'application, cette fonction pourra fournir plus de fonctionnalité
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public abstract void refuse();

    /**
     *
     * Pour l'instant ne fait rien de spécial autre que modifier l'état de l'idée
     * car on décide de ne plus modifier les idées qui sont "validated"
     * mais à l'avenir, pour une future
     * version de l'application, cette fonction pourra fournir plus de fonctionnalité
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public abstract void validate();
}
