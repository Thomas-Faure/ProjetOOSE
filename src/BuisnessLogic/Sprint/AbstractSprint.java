package BuisnessLogic.Sprint;

import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;
import BuisnessLogic.Task.TaskState;

import java.time.LocalDate;
import java.util.List;

/**
 * AbstractSprint
 * @author Guillaume Tessier
 */
public abstract class AbstractSprint {

    int sprintID;
    String sprintName;
    LocalDate beginDate;
    LocalDate endDate;
    List<AbstractTask> taskList;
    int idProject;

    /**
     *
     * @return the sprint id
     */
    public abstract int getSprintID();

    /**
     *
     * @return the sprint name
     */
    public abstract String getSprintName();

    /**
     * Set the sprint name
     * @param sprintName
     */
    public abstract void setSprintName(String sprintName);

    /**
     *
     * @return the begin date of sprint
     */
    public abstract LocalDate getBeginDate();

    /**
     * Set the begin date of a sprint
     * @param beginDate
     */
    public abstract void setBeginDate(LocalDate beginDate);

    /**
     *
     * @return the end date of a sprint
     */
    public abstract LocalDate getEndDate();

    /**
     * Set the end date of a sprint
     * @param endDate
     */
    public abstract void setEndDate(LocalDate endDate);

    /**
     *
     * @return a list of AbstractTask
     */
    public abstract List<AbstractTask> getTaskList();

    /**
     *
     * @param taskS
     * @see TaskState
     * @return a list of AbstractTask depending the TaskState of them
     */
    public abstract List<AbstractTask> getTaskListByState(TaskState taskS);

    /**
     * Set the task list of a sprint
     * @param taskList
     */
    public abstract void setTaskList(List<AbstractTask> taskList);

    /**
     *
     * @return the if of the project corresponding to the sprint
     */
    public abstract int getIdProject();
}
