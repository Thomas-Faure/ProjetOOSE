package DAO;


import BuisnessLogic.Chat.AbstractChat;

public interface ChatDAO {

	boolean save(AbstractChat chat);
	boolean update(AbstractChat chat);
	boolean delete(int idChat);
	AbstractChat getChatByProjectId(int idProject);

}
