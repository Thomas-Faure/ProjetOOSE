package DAO.Chat;


import BusinessLogic.Chat.AbstractChat;

/**
 *
 * @author Guillaume Tessier
 */
public interface IChatDAO {

	/**
	 * Insert a new chat in the database
	 * @param chat
	 * @return true or false, depend if the action succeed
	 */
	boolean save(AbstractChat chat);

	/**
	 * Update a chat in the database
	 * @param chat
	 * @return true or false, depend if the action succeed
	 */
	boolean update(AbstractChat chat);

	/**
	 * Delete a chat in the database
	 * @param idChat
	 * @return true or false, depend if the action succeed
	 */
	boolean delete(int idChat);

	/**
	 * Found the chat corresponding to the project id given in parameter
	 * @param idProject
	 * @return a AbstractChat
	 */
	AbstractChat getChatByProjectId(int idProject);

}
