package DAO.Message;

import BuisnessLogic.Message.AbstractMessage;

import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public interface MessageDAO {

    /**
     * Insert a new message in the database
     * @param message
     * @return true or false, depend if the action succeed
     */
    boolean save(AbstractMessage message);

    /**
     * Delete a message in the database
     * @param idMessage
     * @return true or false, depend if the action succeed
     */
    boolean delete(int idMessage);

    /**
     * Found the message corresponding to the message id and return them
     * @param idMessage
     * @return a AbstractMessage
     */
    AbstractMessage getMessageById(int idMessage);

    /**
     * Found the all messages corresponding to the chat id given in parameter
     * @param idChat
     * @return a list of AbstrastMessage
     */
    List<AbstractMessage> getAllMessageByChat(int idChat);
}
