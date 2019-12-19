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

    @Override
    public boolean addTask(Task task) {
        if(daoFactory.save(task)){
            //on ajouter la nouvelle tache à la liste
            this.tasks.add(task);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean modifyTask(Task task) {
        if(daoFactory.update(task)){
            int i = 0;
            while(this.tasks.get(i).getId() != task.getId()){
                i++;
            }
            this.tasks.set(i, task);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteTask(Task task) {
        if(daoFactory.delete(task.getId())){
            return true;
        }else {
            return false;
        }
    }

}
