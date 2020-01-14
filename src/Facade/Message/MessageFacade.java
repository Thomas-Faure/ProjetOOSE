package Facade.Message;

import BusinessLogic.Message.AbstractMessage;
import DAO.Message.IMessageDAO;
import DAO.MySQLDAOFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public class MessageFacade implements IMessageFacade {
    private List<AbstractMessage> messageList;
    private IMessageDAO dao;
    public static MessageFacade FacadeInstance;

    private MessageFacade(){
        this.dao = MySQLDAOFactory.getInstance().getMessageDAO();
        this.messageList = new ArrayList<>();
    }

    @Override
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
