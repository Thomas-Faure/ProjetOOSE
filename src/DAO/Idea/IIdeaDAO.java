package DAO.Idea;


import BusinessLogic.Idea.Idea;
import BusinessLogic.Idea.AbstractIdea;

import java.util.List;

/**
 * Cette Interface correspond au DAO qui gère les idées.
 * IL est en lien avec la base de données sur laquelle il fait des
 * requetes pour récupérer des données.
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public interface IIdeaDAO {

	/**
	 * Fonction non utile pour cette version de l'application
	 * Cette fonction premet de créer et retourner
	 * une idée par rapport à une idée de la base de donnée
	 * dont l'id est passé en paramètre.
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @param id : L'id de l'idée qu'on veut récuperer de la base de donnée
	 */
	public Idea createIdeaById(int id);

	/**
	 * Permet d'insérer dans la base de donnée une idée passée
	 * en paramètre
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @param idea : AbstractIdea
	 */
	public boolean save(AbstractIdea idea);

	/**
	 * Permet de modifier dans la base de donnée une idée passée
	 * en paramètre
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @param idea : Idée qu'on veut modifier
	 */
	public boolean update(AbstractIdea idea);

	/**
	 * Permet de supprimer dans la base de donnée une idée dont
	 * l'id est passé en paramètre
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @param id : ID de l'idée qu'on veut supprimer
	 */
	public boolean delete(int id);

	/**
	 * Retourne la liste de toutes les idées présentes dans
	 * la base de données
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 */
	List<AbstractIdea> getAllIdeas();

	/**
	 * Retourne l'idée dont l'id est passé en paramètre
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 */
	AbstractIdea getIdeaById(int id);

}

