package Facade;

import BuisnessLogic.Task.Task;
import BuisnessLogic.User.User;

public interface ISessionFacade {

    public boolean addTask(Task task);
    public User getUser();

}
