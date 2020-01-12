package BuisnessLogic.Message;

import BuisnessLogic.User.AbstractUser;

/**
 *
 * @author Guillaume Tessier
 */
public class Message extends AbstractMessage {

    int idMessage;
    String contenu;
    int idChat;
    AbstractUser createur;

    /**
     * Constructor with 3 parameters
     * @param contenu
     * @param idChat
     * @param createur
     */
    public Message(String contenu, int idChat, AbstractUser createur) {
        this.contenu = contenu;
        this.idChat = idChat;
        this.createur = createur;
    }

    /**
     * Constructor with 4 parameters
     * @param idMessage
     * @param contenu
     * @param idChat
     * @param createur
     */
    public Message(int idMessage, String contenu, int idChat, AbstractUser createur) {
        this.idMessage = idMessage;
        this.contenu = contenu;
        this.idChat = idChat;
        this.createur = createur;
    }

    @Override
    public int getIdMessage() {
        return this.idMessage;
    }

    @Override
    public String getContenu() {
        return this.contenu;
    }

    @Override
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public int getIdChat() {
        return this.idChat;
    }

    @Override
    public AbstractUser getCreateur() {
        return this.createur;
    }
}
