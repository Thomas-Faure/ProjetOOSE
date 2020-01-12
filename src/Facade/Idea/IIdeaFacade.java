package Facade.Idea;

import BuisnessLogic.Idea.AbstractIdea;

import java.util.List;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Facade correspond à la façade qui gère les idées.
 * Il aura une instance statique à partir de laquelle on pourra récupérer
 * les idées présentes dans l'application. Est en lien avec le DAO IdeaDAO
 * correspondant pour récupérer les données depuis la base.
 */
public interface IIdeaFacade {

    boolean addIdea(AbstractIdea idea);

    boolean modifyIdea(AbstractIdea idea);

    boolean deleteIdea(AbstractIdea idea);

    //List<AbstractIdea> getIdeaByName(String name);

    boolean getAllIdeas();

    AbstractIdea getIdeaById(int id);

    List<AbstractIdea> getListIdeas();
}
