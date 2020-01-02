package Facade.Idea;

import BuisnessLogic.Idea.AbstractIdea;

public interface IIdeaFacade {

    boolean addIdea(AbstractIdea idea);

    boolean modifyIdea(AbstractIdea idea);

    boolean deleteIdea(AbstractIdea idea);

    //List<AbstractIdea> getIdeaByName(String name);

    boolean getAllIdeas();

    AbstractIdea getIdeaById(int id);
}
