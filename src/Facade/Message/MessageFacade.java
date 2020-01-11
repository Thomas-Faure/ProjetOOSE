package Facade.Message;

import BuisnessLogic.Message.AbstractMessage;
import DAO.Message.MessageDAO;
import DAO.MySQLDAOFactory;
import org.omg.DynamicAny.DynAnyOperations;

import java.util.ArrayList;
import java.util.List;

public class MessageFacade implements IMessageFacade {
    private List<AbstractMessage> messageList;
    private MessageDAO dao;
    public static MessageFacade FacadeInstance;

    private MessageFacade(){
        this.dao = MySQLDAOFactory.getInstance().getMessageDAO();
        this.messageList = new ArrayList<>();
    }

    public List<AbstractMessage> getMessageList() {
        return messageList;
    }

    public static MessageFacade getInstance(){
        if(FacadeInstance == null){
            FacadeInstance = new MessageFacade();
        }
        return FacadeInstance;
    }

    
    @Override
    public boolean addMessage(AbstractMessage message) {
        return dao.save(message);
    }

    @Override
    public boolean deleteMessage(AbstractMessage message) {
        return dao.delete(message.getIdMessage());
    }


    @Override
    public boolean getAllMessageByChat(int idChat) {
        this.messageList = dao.getAllMessageByChat(idChat);
        return (messageList!=null);
    }
}
