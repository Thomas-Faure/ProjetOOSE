package Facade;

import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;
import DAO.AbstractDAOFactory;
import DAO.MySQLDAOFactory;
import DAO.TaskDAO;

import java.util.ArrayList;
import java.util.List;

public class TaskFacade implements ITaskFacade {
    private List<AbstractTask> tasks;
    private TaskDAO daoFactory;


    public TaskFacade(){
        daoFactory = MySQLDAOFactory.getTaskDAO();
        this.tasks = new ArrayList<>();
    }

}
