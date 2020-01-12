package Facade;

import BuisnessLogic.Chat.AbstractChat;
import BuisnessLogic.Message.AbstractMessage;
import DAO.ChatDAO;
import DAO.MySQLDAOFactory;
import Facade.Message.MessageFacade;
import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public class ChatFacade implements IChatFacade {
    private ChatDAO dao;
    private AbstractChat currentChat;
    private static ChatFacade FacadeInstance;

    private ChatFacade(){
        this.dao = MySQLDAOFactory.getInstance().getChatDAO();
    }


    public static ChatFacade getInstance(){
        if(FacadeInstance == null){
            FacadeInstance = new ChatFacade();
        }
        return FacadeInstance;
    }

    @Override
    public AbstractChat getCurrentChat() {
        return currentChat;
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
    public boolean getChatByProjectId(int idProject) {
        boolean result = false;
        AbstractChat chat = dao.getChatByProjectId(idProject);

        if(chat!=null) {
            MessageFacade messageFacade = MessageFacade.getInstance();
            messageFacade.getAllMessageByChat(chat.getIdChat());
            List<AbstractMessage> messageStored = messageFacade.getMessageList();
            chat.setHistoriqueMessage(messageStored);
            this.currentChat = chat;
            result=true;
        }


        return result;
    }

}
