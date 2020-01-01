package Facade.Project;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Ticket.AbstractTicket;
import DAO.AbstractDAOFactory;
import DAO.MySQLDAOFactory;
import DAO.Project.ProjectDAO;
import DAO.Ticket.TicketDAO;
import Facade.Ticket.TicketFacade;

import java.util.ArrayList;
import java.util.List;

public class ProjectFacade implements IProjectFacade {
    private List<AbstractProject> projects;
    private ProjectDAO dao;
    public static ProjectFacade instance;

    private ProjectFacade(){
        dao = MySQLDAOFactory.getProjectDAO();
        this.projects = new ArrayList<>();
    }

    public static ProjectFacade getInstance(){
        if(instance == null){
            instance =new ProjectFacade();
        }
        return instance;
    }

    @Override
    public boolean getAllProjects() {
        this.projects = dao.getAllProjects();
        return true;
    }

    @Override
    public List<AbstractProject> getListProjects() {
        return this.projects;
    }

    @Override
    public boolean deleteProject(AbstractProject project) {
        if(dao.delete(project.getId())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addProject(AbstractProject project) {
        if(dao.save(project)){
            //on ajouter la nouvelle tache à la liste
            this.projects.add(project);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean update(AbstractProject project) {
        if(dao.update(project)){
            int i = 0;
            while(this.projects.get(i).getId() != project.getId()){
                i++;
            }
            this.projects.set(i, project);
            return true;
        }else {
            return false;
        }
    }
}
