package BuisnessLogic.Chat;

import BuisnessLogic.Message.AbstractMessage;

import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public abstract class AbstractChat {

    /**
     *
     * @return the chat id
     */
    public abstract int getIdChat();

    /**
     *
     * @return the chat name
     */
    public abstract String getChatName();

    /**
     *
     * @param chatName
     */
    public abstract void setChatName(String chatName);

    /**
     *
     * @return the project id corresponding to the chat
     */
    public abstract int getIdProject();

    /**
     *
     * @param idProject
     */
    public abstract void setIdProject(int idProject);

    /**
     * This list contain all messages send into the chat and also stored in db
     * @return a list of AbstractMessage
     */
    public abstract List<AbstractMessage> getHistoriqueMessage();

    /**
     *
     * @param historiqueMessage a list of AbstractMessage
     */
    public abstract void setHistoriqueMessage(List<AbstractMessage> historiqueMessage);
}
