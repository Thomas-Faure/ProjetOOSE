package Facade.Chat;

import BusinessLogic.Chat.AbstractChat;
import BusinessLogic.Message.AbstractMessage;
import DAO.Chat.IChatDAO;
import DAO.MySQLDAOFactory;
import Facade.Message.MessageFacade;
import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public class ChatFacade implements IChatFacade {
    private IChatDAO dao;
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
            this.currentChat = chat;
            MessageFacade messageFacade = MessageFacade.getInstance();
            messageFacade.getAllMessageByChat(chat.getIdChat());
            List<AbstractMessage> messageStored = messageFacade.getMessageList();
            this.currentChat.setHistoriqueMessage(messageStored);
            result=true;
        }
        return result;
    }

}
