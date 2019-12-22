package Facade;

import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;

import java.util.List;

public interface ITaskFacade {

      boolean addTask(Task task);

      boolean modifyTask(Task task);

      boolean deleteTask(Task task);

      List<AbstractTask> getTaskByName(String name);

      boolean getAllTasks();

      AbstractTask getTaskById(int id);

}
