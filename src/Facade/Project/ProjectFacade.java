package Facade.Project;

import BuisnessLogic.Project.AbstractProject;
import DAO.MySQLDAOFactory;
import DAO.Project.ProjectDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette facade permet de gérer les projets
 * @author Rémi Salmi
 */
public class ProjectFacade implements IProjectFacade {
    private List<AbstractProject> projects;
    private ProjectDAO dao;
    public static ProjectFacade instance;

    private ProjectFacade(){
        dao = MySQLDAOFactory.getInstance().getProjectDAO();
        this.projects = new ArrayList<>();
    }

    /**
     * Retourne l'instance de la facade
     * @author Rémi Salmi
     */
    public static ProjectFacade getInstance(){
        if(instance == null){
            instance =new ProjectFacade();
        }
        return instance;
    }

    @Override
    /**
     * Permet de récupérer tous les projets
     * @author Rémi Salmi
     */
    public boolean getAllProjects() {
        this.projects = dao.getAllProjects();
        return true;
    }

    @Override
    /**
     * Renvoie la liste des projets de la facade
     * @author Rémi Salmi
     */
    public List<AbstractProject> getListProjects() {
        return this.projects;
    }

    @Override
    /**
     * Permet de supprimer un projet
     * @author Rémi Salmi
     */
    public boolean deleteProject(AbstractProject project) {
        if(dao.delete(project.getId())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    /**
     * permet de récupérer un projet par son id
     * @author Rémi Salmi
     */
    public AbstractProject getProjectById(int id) {
        AbstractProject project;
        project = dao.getProjectById(id);
        return project;
    }

    @Override
    /**
     * permet de creer un projet
     * @author Rémi Salmi
     */
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
    /**
     * Permet de mettre a jour un projet
     * @author Rémi Salmi
     */
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
