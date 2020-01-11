package Facade;

import BuisnessLogic.Chat.AbstractChat;
import BuisnessLogic.Chat.Message;
import DAO.AbstractDAOFactory;
import DAO.ChatDAO;
import DAO.MySQLDAOFactory;

import java.util.ArrayList;
import java.util.List;

public class ChatFacade implements IChatFacade {
    private ChatDAO dao;
    private AbstractChat[] chats;
    public static ChatFacade FacadeInstance;

    private ChatFacade(){
        this.dao = MySQLDAOFactory.getChatDAO();
    }

    public static ChatFacade getInstance(){
        if(FacadeInstance == null){
            FacadeInstance = new ChatFacade();
        }
        return FacadeInstance;
    }

    @Override
    public boolean addChat(AbstractChat chat) {
        return dao.save(chat);
    }

    @Override
    public boolean deleteChat(AbstractChat chat) {
        return dao.delete(chat.getIdChat());
    }

    @Override
    public boolean updateChat(AbstractChat chat) {
        return dao.update(chat);
    }

    @Override
    public AbstractChat getChatByProjectId(int idProject) {
        AbstractChat chat = dao.getChatByProjectId(idProject);

        //FacadeMessage.getMessageByChat(idChat)
        //List<Message> messageStored = new ArrayList<Message>();
        //chat.setHistoriqueMessage(messageStored);

        return chat;
    }
}
