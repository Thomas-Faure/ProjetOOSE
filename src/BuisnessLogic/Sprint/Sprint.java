package BuisnessLogic.Sprint;

import BuisnessLogic.Task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sprint extends AbstractSprint {
    String sprintName;
    LocalDate beginDate;
    LocalDate endDate;
    List<Task> taskList;

    public Sprint(String sprintName, LocalDate beginDate, LocalDate endDate) {
        this.sprintName = sprintName;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.taskList = new ArrayList<Task>();
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
    public List<Task> getTaskList() {
        return this.taskList;
    }

    @Override
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
