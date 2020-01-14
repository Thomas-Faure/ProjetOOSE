package Facade;

import BusinessLogic.Chat.AbstractChat;

/**
 *
 * @author Guillaume Tessier
 */
public interface IChatFacade {

    /**
     *
     * @return a AbstractChat as the current chat. The last returned by DAO
     */
    AbstractChat getCurrentChat();

    /**
     * Access to the DAO to insert a new chat in the database
     * @param chat
     * @return true or false, depend if the action succeed
     */
    boolean addChat(AbstractChat chat);

    /**
     * Access to the DAO to delete a chat in the database
     * @param chat
     * @return true or false, depend if the action succeed
     */
    boolean deleteChat(AbstractChat chat);

    /**
     * Access to the DAO to update a chat in the database
     * @param chat
     * @return true or false, depend if the action succeed
     */
    boolean updateChat(AbstractChat chat);

    /**
     * Access to the DAO to return a chat from the database. Store the chat in currentChat attribute of ChatFacade.
     * Retrieve all the message corresponding to the chat returned and use setHistoriqueMessage() from chat object to set the chat list of message.
     * @param idProject
     * @return true or false, depend if the action succeed
     */
    boolean getChatByProjectId(int idProject);
}
