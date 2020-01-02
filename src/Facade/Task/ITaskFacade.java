package Facade.Task;

import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;

import java.util.List;

public interface ITaskFacade {

      boolean addTask(AbstractTask task);

      boolean modifyTask(AbstractTask task);

      boolean deleteTask(AbstractTask task);

      List<AbstractTask> getTaskByName(String name);

      boolean getAllTasks();

      AbstractTask getTaskById(int id);

}
