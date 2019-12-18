package Facade;

import BuisnessLogic.Task.AbstractTask;
import DAO.AbstractDAOFactory;

public class TaskFacade implements ITaskFacade {
    private AbstractTask[] tasks;
    private AbstractDAOFactory daoFactory;
}
