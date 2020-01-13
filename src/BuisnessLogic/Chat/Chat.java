package BuisnessLogic.Chat;

import BuisnessLogic.Message.AbstractMessage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public class Chat extends AbstractChat {
    private int idChat;
    private String chatName;
    private List<AbstractMessage> historiqueMessage;
    private int idProject;

    /**
     * Constructor with 2 parameters
     * @param chatName
     * @param idProject
     */
    public Chat(String chatName, int idProject) {
        this.chatName = chatName;
        this.idProject = idProject;
        this.historiqueMessage = new ArrayList<AbstractMessage>();
    }

    /**
     * Constructor with 3 parameters
     * @param idChat
     * @param chatName
     * @param idProject
     */
    public Chat(int idChat, String chatName, int idProject) {
        this.idChat = idChat;
        this.chatName = chatName;
        this.idProject = idProject;
        this.historiqueMessage = new ArrayList<AbstractMessage>();
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
    public void setIdProject(int idProject) {
        this.idProject=idProject;
    }

    @Override
    public List<AbstractMessage> getHistoriqueMessage() {
        return this.historiqueMessage;
    }

    @Override
    public void setHistoriqueMessage(List<AbstractMessage> historiqueMessage) {
        this.historiqueMessage = historiqueMessage;
    }
}
