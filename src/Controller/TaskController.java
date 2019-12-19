package Controller;

import BuisnessLogic.Task.Task;
import BuisnessLogic.User.User;
import Facade.ISessionFacade;
import Facade.ITaskFacade;
import Facade.SessionFacade;
import Facade.TaskFacade;

import java.time.LocalDate;

public class TaskController {
    private ITaskFacade tFacade;

    public TaskController(){
        tFacade = new TaskFacade();

    }
    public boolean modifyTask(int id,String subject,String description, int priority, LocalDate deadline){

        Task task = new Task(id,subject,description,priority,deadline,SessionFacade.getInstance().getUser());
        return tFacade.modifyTask(task);
    }
    public boolean addTask(String subject,String description, int priority, LocalDate deadline){

        Task task = new Task(0,subject,description,priority,deadline,SessionFacade.getInstance().getUser());
        return tFacade.addTask(task);
    }
}
