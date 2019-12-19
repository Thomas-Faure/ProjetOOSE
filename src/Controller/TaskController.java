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
    private ISessionFacade sFacade;
    public TaskController(){
        tFacade = new TaskFacade();

    }
    public boolean addTask(String subject,String description, int priority, LocalDate deadline){

        Task task = new Task(0,subject,description,priority,deadline,sFacade.getUser());
        return sFacade.addTask(task);
    }
}
