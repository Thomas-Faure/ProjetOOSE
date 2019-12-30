package Facade;

import BuisnessLogic.Sprint.AbstractSprint;

import java.util.List;

public interface ISprintFacade {

    boolean addSprint(AbstractSprint sprint);
    boolean deleteSprint(int sprintID);
    AbstractSprint getSprintById(int sprintID);
    List<AbstractSprint> getListSprintByProject(int projectID);
}
