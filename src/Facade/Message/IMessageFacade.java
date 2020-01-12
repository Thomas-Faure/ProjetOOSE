package Facade.Message;

import BuisnessLogic.Message.AbstractMessage;

import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public interface IMessageFacade {

    /**
     *
     * @return a list of AbstractMessage
     */
    List<AbstractMessage> getMessageList();

    /**
     * Access to the DAO to insert a new message in the database
     * @param message
     * @return true or false, depend if the action succeed
     */
    boolean addMessage(AbstractMessage message);

    /**
     * Access to the DAO to delete a message in the database
     * @param message
     * @return true or false, depend if the action succeed
     */
    boolean deleteMessage(AbstractMessage message);

    //AbstractMessage getMessageById(int idProject);

    /**
     * Access to the DAO to get all the messages relative to a chat.
     * Store a list of AbstractMessage in messageList attribute of MessageFacade.
     * @param idChat
     * @return true or false, depend if the action succeed
     */
    boolean getAllMessageByChat(int idChat);
    
}
