package Facade;

import BuisnessLogic.Idea.AbstractIdea;
import DAO.AbstractDAOFactory;

public class IdeaFacade implements IIdeaFacade {
    private AbstractIdea[] ideas;
    private AbstractDAOFactory daoFactory;
}
