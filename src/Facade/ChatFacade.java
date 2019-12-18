package Facade;

import BuisnessLogic.Chat.AbstractChat;
import DAO.AbstractDAOFactory;

public class ChatFacade implements IChatFacade {
    private AbstractDAOFactory daoFactory;
    private AbstractChat[] chats;
}
