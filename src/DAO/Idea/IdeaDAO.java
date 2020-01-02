package DAO.Idea;


import BuisnessLogic.Idea.Idea;
import BuisnessLogic.Idea.AbstractIdea;

import java.util.List;

public interface IdeaDAO {
	public Idea createIdeaById(int id);
	public boolean save(AbstractIdea idea);
	public boolean update(AbstractIdea idea);
	public boolean delete(int id);
	List<AbstractIdea> getAllIdeas();
	AbstractIdea getIdeaById(int id);
	List<AbstractIdea> getIdeaByName(String name);

}
