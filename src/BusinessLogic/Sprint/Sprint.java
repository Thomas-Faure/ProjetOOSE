package BusinessLogic.Sprint;

import BusinessLogic.Task.AbstractTask;
import BusinessLogic.Task.TaskState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public class Sprint extends AbstractSprint {
    int sprintID;
    String sprintName;
    LocalDate beginDate;
    LocalDate endDate;
    List<AbstractTask> taskList;
    int idProject;

    /**
     * Constructor with 4 parameters
     * @param sprintName
     * @param beginDate
     * @param endDate
     * @param idProject
     */
    public Sprint(String sprintName, LocalDate beginDate, LocalDate endDate, int idProject) {
        this.sprintName = sprintName;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.taskList = new ArrayList<AbstractTask>();
        this.idProject = idProject;
    }

    /**
     * Constructor with 5 parameters
     * @param sprintID
     * @param sprintName
     * @param beginDate
     * @param endDate
     * @param idProject
     */
    public Sprint(int sprintID, String sprintName, LocalDate beginDate, LocalDate endDate, int idProject) {
        this.sprintID = sprintID;
        this.sprintName = sprintName;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.taskList = new ArrayList<AbstractTask>();
        this.idProject = idProject;
    }

    @Override
    public int getSprintID() {
        return this.sprintID;
    }

    @Override
    public String getSprintName() {
        return this.sprintName;
    }

    @Override
    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    @Override
    public LocalDate getBeginDate() {
        return this.beginDate;
    }

    @Override
    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    @Override
    public LocalDate getEndDate() {
        return this.endDate;
    }

    @Override
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public List<AbstractTask> getTaskList() {
        return this.taskList;
    }

    @Override
    public List<AbstractTask> getTaskListByState(TaskState taskS) {
        List<AbstractTask> taskListSate = new ArrayList<AbstractTask>();
        for(AbstractTask task: this.taskList){
            if (task.getState().equals(taskS)){
                taskListSate.add(task);
            }
        }
        return taskListSate;
    }

    @Override
    public void setTaskList(List<AbstractTask> taskList) {
        this.taskList = taskList;
    }

    @Override
    public int getIdProject() {
        return this.idProject;
    }
}
