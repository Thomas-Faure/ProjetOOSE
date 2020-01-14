package BusinessLogic.Message;

import BusinessLogic.User.AbstractUser;

/**
 *
 * @author Guillaume Tessier
 */
public abstract class AbstractMessage {

    /**
     *
     * @return the message id
     */
    public abstract int getIdMessage();

    /**
     *
     * @return the content of the message
     */
    public abstract String getContenu();

    /**
     *
     * @param contenu
     */
    public abstract void setContenu(String contenu);

    /**
     *
     * @return the chat id corresponding to the message
     */
    public abstract int getIdChat();

    /**
     *
     * @return the AbstractUser who created the message
     */
    public abstract AbstractUser getCreateur();
}
