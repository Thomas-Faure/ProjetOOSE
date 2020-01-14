package Facade.Idea;

import BusinessLogic.Idea.AbstractIdea;
import DAO.Idea.IIdeaDAO;
import DAO.MySQLDAOFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * Cette Classe correspond à la façade qui gère les idées.
 * Il aura une instance statique à partir de laquelle on pourra récupérer
 * les idées présentes dans l'application. Est en lien avec le DAO IdeaDAO
 * correspondant pour récupérer les données depuis la base.
 */
public class IdeaFacade implements IIdeaFacade {
    private List<AbstractIdea> ideas;
    private IIdeaDAO daoFactory;

    public static IdeaFacade instance;

    private IdeaFacade(){
        daoFactory = MySQLDAOFactory.getInstance().getIdeaDAO();
        this.ideas = new ArrayList<>();
    }

    public static IdeaFacade getInstance(){
        if(instance == null){
            instance = new IdeaFacade();
        }
        return instance;
    }

    public List<AbstractIdea> getListIdeas(){
        return this.ideas;
    }

    public IIdeaDAO getDao(){
        return this.daoFactory;
    }


    public boolean addIdea(AbstractIdea idea) {
        if(instance.getDao().save(idea)){
            //on ajouter la nouvelle idee à la liste
            instance.ideas.add(idea);
            return true;
        }else {
            return false;
        }
    }

    public boolean modifyIdea(AbstractIdea idea) {
        if(daoFactory.update(idea)){
            int i = 0;
            while(this.ideas.get(i).getId() != idea.getId()){
                i++;
            }
            this.ideas.set(i, idea);
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteIdea(AbstractIdea idea){
        if(daoFactory.delete(idea.getId())){
            return true;
        }
        else {
            return false;
        }
    }

    /*public List<AbstractIdea> getIdeaByName(String name) {
        return null;
    }*/

    public boolean getAllIdeas() {
        this.ideas = daoFactory.getAllIdeas();
        return true;
    }

    public AbstractIdea getIdeaById(int id) {
        AbstractIdea idea;
        idea = daoFactory.getIdeaById(id);
        return idea;
    }

}
