package Controller;

import BuisnessLogic.Task.Task;
import BuisnessLogic.User.User;
import Facade.ISessionFacade;
import Facade.ITaskFacade;
import Facade.SessionFacade;
import Facade.TaskFacade;

import java.time.LocalDate;

public class TaskController {
    public static TaskController instance;

    private TaskController(){


    }
    public static TaskController getInstance(){
        if(instance == null){
            instance= new TaskController();
        }
        return instance;
    }
    public static boolean modifyTask(int id,String subject,String description, int priority, LocalDate deadline){

        Task task = new Task(id,subject,description,priority,deadline,SessionFacade.getInstance().getUser());
        return TaskFacade.getInstance().modifyTask(task);
    }
    public boolean addTask(String subject,String description, int priority, LocalDate deadline){

        Task task = new Task(0,subject,description,priority,deadline,SessionFacade.getInstance().getUser());
        return TaskFacade.getInstance().addTask(task);
    }
}
