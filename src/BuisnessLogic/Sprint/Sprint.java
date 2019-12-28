package BuisnessLogic.Sprint;

import Facade.Task.ITaskFacade;

import java.util.Date;

public class Sprint extends AbstractSprint {
    private String name;
    private Date beginDate;
    private Date endDate;
    private ITaskFacade tasks;
}
