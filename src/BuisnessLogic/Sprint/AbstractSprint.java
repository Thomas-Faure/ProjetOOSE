package BuisnessLogic.Sprint;

import BuisnessLogic.Task.Task;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractSprint {

    int sprintID;
    String sprintName;
    LocalDate beginDate;
    LocalDate endDate;
    List<Task> taskList;

    public abstract int getSprintID();

    public abstract String getSprintName();

    public abstract void setSprintName(String sprintName);

    public abstract LocalDate getBeginDate();

    public abstract void setBeginDate(LocalDate beginDate);

    public abstract LocalDate getEndDate();

    public abstract void setEndDate(LocalDate endDate);

    public abstract List<Task> getTaskList();

    public abstract void setTaskList(List<Task> taskList);
}
