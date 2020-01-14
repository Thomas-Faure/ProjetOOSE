package Facade;

import BusinessLogic.Idea.AbstractIdea;
import BusinessLogic.Idea.Idea;
import DAO.Idea.IdeaDAO;
import DAO.MySQLDAOFactory;

import java.util.ArrayList;
import java.util.List;

public class IdeaFacade implements IIdeaFacade {
    private List<AbstractIdea> ideas;
    private IdeaDAO daoFactory;

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

    public IdeaDAO getDao(){
        return this.daoFactory;
    }


    public boolean addIdea(Idea idea) {
        if(instance.getDao().save(idea)){
            //on ajouter la nouvelle idee à la liste
            instance.ideas.add(idea);
            return true;
        }else {
            return false;
        }
    }

    public boolean modifyIdea(Idea idea) {
        if(daoFactory.update(idea)){
            this.ideas.set(idea.getId(), idea);
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteIdea(Idea idea){
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
