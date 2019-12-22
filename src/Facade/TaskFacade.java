package Facade;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;
import BuisnessLogic.User.AbstractUser;
import DAO.AbstractDAOFactory;
import DAO.MySQLDAOFactory;
import DAO.TaskDAO;

import java.util.ArrayList;
import java.util.List;

public class TaskFacade implements ITaskFacade {
    private List<AbstractTask> tasks;
    private TaskDAO daoFactory;


    public static TaskFacade instance;
    private TaskFacade(){
        daoFactory = MySQLDAOFactory.getTaskDAO();
        this.tasks = new ArrayList<>();
    }

    public static TaskFacade getInstance(){
        if(instance == null){
            instance = new TaskFacade();
        }
        return instance;
    }


    public List<AbstractTask> getListTasks(){
        return this.tasks;
    }

    public TaskDAO getDao(){
        return this.daoFactory;
    }

    @Override
    public  boolean addTask(Task task) {
        if(instance.getDao().save(task)){
            //on ajouter la nouvelle tache à la liste
            instance.tasks.add(task);
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
    @Override
    public AbstractTask getTaskById(int id){
        AbstractTask task;
        task = daoFactory.getTaskById(id);
        return task;
    }

    @Override
    public List<AbstractTask> getTaskByName(String name){
        List<AbstractTask> task=daoFactory.getTaskByName(name);

        return task;
    }

    @Override
    public boolean getAllTasks() {
        this.tasks = daoFactory.getAllTasks();
        return true;
    }

}
