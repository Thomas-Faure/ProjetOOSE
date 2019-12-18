package DAO;


import BuisnessLogic.Idea.Idea;

public interface IdeaDAO {
	public Idea createIdeaById(int id);

	public boolean save();
	public boolean update();

}
