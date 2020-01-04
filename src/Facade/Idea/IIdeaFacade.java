package Facade.Idea;

import BuisnessLogic.Idea.AbstractIdea;

import java.util.List;

public interface IIdeaFacade {

    boolean addIdea(AbstractIdea idea);

    boolean modifyIdea(AbstractIdea idea);

    boolean deleteIdea(AbstractIdea idea);

    //List<AbstractIdea> getIdeaByName(String name);

    boolean getAllIdeas();

    AbstractIdea getIdeaById(int id);

    List<AbstractIdea> getListIdeas();
}
