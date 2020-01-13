package BusinessLogic.Idea;


import BusinessLogic.User.AbstractUser;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette classe correspond aux idées que les utilisateurs
 * peuvent publier pour proposer des suggestions
 */
public class Idea extends AbstractIdea {

	int id;
	String name;
	String description;
	String subject;
	String state = null;
	AbstractUser creator;
	public Idea(int id, String name,String description,String subject, AbstractUser creator) {
		this.id = id;
		this.name=name;
		this.description=description;
		this.subject=subject;
		this.creator = creator;
		
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public AbstractUser getCreator() {
		return this.creator;
	}

	public String getDescription() {
		return this.description;
	}

	public String getSubject() {
		return this.subject;
	}

	public String getState(){ return this.state;}


	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreator(AbstractUser creator) {
		this.creator = creator;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 *
	 * @param state Doit être soit "Validated", soit "Refused"
	 * @Description Simple accesseur mais privilégiez les fonctions refuse() et validate()
	 */
	public void setState(String state) { this.state = state; }


	/**
	 * @Description : Pour l'instant ne fait rien de spécial autre que modifier l'état de
	 * l'idée car on décide de supprimer les
	 * idées qui sont "Refused", mais si l'on décide de garder un historique pour une future
	 * version de l'application, cette fonction pourra fournir plus de fonctionnalité
	 */
	public void refuse() {
		setState("Refused");
	}

	/**
	 * @Description : Pour l'instant ne fait rien de spécial autre que modifier l'état de l'idée
	 * car on décide de ne plus modifier les idées qui sont "validated"
	 * mais à l'avenir, pour une future
	 * version de l'application, cette fonction pourra fournir plus de fonctionnalité
	 */
	public void validate() {
		setState("Validated");
	}
}
