package Facade.Project;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Ticket.AbstractTicket;

import java.util.List;

public interface IProjectFacade {
    boolean getAllProjects();

    List<AbstractProject> getListProjects();

    boolean deleteProject(AbstractProject project);

    boolean addProject(AbstractProject project);

    boolean update(AbstractProject project);
}
