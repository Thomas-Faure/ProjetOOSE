package BuisnessLogic.Chat;

import BuisnessLogic.User.GlobalUser;

public class Message extends AbstractMessage {

    int idMessage;
    String contenu;
    int idChat;
    GlobalUser createur;

    public Message(String contenu, int idChat, GlobalUser createur) {
        this.contenu = contenu;
        this.idChat = idChat;
        this.createur = createur;
    }

    public Message(int idMessage, String contenu, int idChat, GlobalUser createur) {
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
    public GlobalUser getCreateur() {
        return this.createur;
    }
}
