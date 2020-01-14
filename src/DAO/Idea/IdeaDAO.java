package DAO.Idea;


import BusinessLogic.Idea.Idea;
import BusinessLogic.Idea.AbstractIdea;

import java.util.List;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Interface correspond au DAO qui gère les idées.
 * IL est en lien avec la base de données sur laquelle il fait des
 * requetes pour récupérer des données.
 */
public interface IdeaDAO {

	/**
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @Description Fonction non utile pour cette version de l'application
	 * Cette fonction premet de créer et retourner
	 * une idée par rapport à une idée de la base de donnée
	 * dont l'id est passé en paramètre.
	 * @Param id : L'id de l'idée qu'on veut récuperer de la base de donnée
	 */
	public Idea createIdeaById(int id);

	/**
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @Description Permet d'insérer dans la base de donnée une idée passée
	 * en paramètre
	 * @Param AbstractIdea
	 */
	public boolean save(AbstractIdea idea);

	/**
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @Description Permet de modifier dans la base de donnée une idée passée
	 * en paramètre
	 * @Param idea : Idée qu'on veut modifier
	 */
	public boolean update(AbstractIdea idea);

	/**
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @Description Permet de supprimer dans la base de donnée une idée dont
	 * l'id est passé en paramètree
	 * @Param id : ID de l'idée qu'on veut supprimer
	 */
	public boolean delete(int id);

	/**
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @Description Retourne la liste de toutes les idées présentes dans
	 * la base de données
	 */
	List<AbstractIdea> getAllIdeas();

	/**
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @Description Retourne l'idée dont l'id est passé en paramètre
	 */
	AbstractIdea getIdeaById(int id);

}
