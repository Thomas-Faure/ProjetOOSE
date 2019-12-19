package BuisnessLogic.Project;

import Facade.IMeetingFacade;
import Facade.IMemberFacade;
import Facade.ISprintFacade;
import Facade.ITaskFacade;

import java.util.Date;

public class Project extends AbstractProject {
    private String name;
    private String description;
    private IMemberFacade members;
    private IMeetingFacade meetings;
    private ISprintFacade sprints;
    private ITaskFacade tasks;
    private Date dateCreation;
}