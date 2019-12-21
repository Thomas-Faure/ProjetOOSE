package Facade;

import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;

public interface ITaskFacade {

      boolean addTask(Task task);

      boolean modifyTask(Task task);

      boolean deleteTask(Task task);

      boolean getAllTasks();

      AbstractTask getTaskById(int id);

}
