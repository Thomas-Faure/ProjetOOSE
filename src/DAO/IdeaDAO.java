package DAO;

import Announcement.Announcement;
import Idea.Idea;

public interface IdeaDAO {
	public Idea createIdeaById(int id);

	public boolean save();
	public boolean update();

}
