package BuisnessLogic.Chat;

import BuisnessLogic.User.User;

import java.util.Date;
import java.util.List;

public class Chat extends AbstractChat {
    private int idChat;
    private String chatName;
    private List<Message> historiqueMessage;
    private int idProject;

    public Chat(String chatName, int idProject) {
        this.chatName = chatName;
        this.idProject = idProject;
    }

    public Chat(int idChat, String chatName, List<Message> historiqueMessage, int idProject) {
        this.idChat = idChat;
        this.chatName = chatName;
        this.historiqueMessage = historiqueMessage;
        this.idProject = idProject;
    }

    @Override
    public int getIdChat() {
        return this.idChat;
    }

    @Override
    public String getChatName() {
        return this.chatName;
    }

    @Override
    public void setChatName(String chatName) {
        this.chatName=chatName;
    }

    @Override
    public int getIdProject() {
        return this.idProject;
    }

    @Override
    public void setIdProject() {
        this.idProject=idProject;
    }

    @Override
    public List<Message> getHistoriqueMessage() {
        return this.historiqueMessage;
    }

    @Override
    public void setHistoriqueMessage(List<Message> historiqueMessage) {
        this.historiqueMessage = historiqueMessage;
    }
}
