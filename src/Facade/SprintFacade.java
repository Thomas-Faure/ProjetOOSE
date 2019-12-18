package Facade;

import BuisnessLogic.Sprint.AbstractSprint;
import DAO.AbstractDAOFactory;

public class SprintFacade implements ISprintFacade {
    private AbstractSprint[] sprints;
    private AbstractDAOFactory daoFactory;
}
