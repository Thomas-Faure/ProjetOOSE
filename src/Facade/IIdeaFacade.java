package Facade;

import BusinessLogic.Idea.AbstractIdea;
import BusinessLogic.Idea.Idea;

public interface IIdeaFacade {

    boolean addIdea(Idea idea);

    boolean modifyIdea(Idea idea);

    boolean deleteIdea(Idea idea);

    //List<AbstractIdea> getIdeaByName(String name);

    boolean getAllIdeas();

    AbstractIdea getIdeaById(int id);
}
