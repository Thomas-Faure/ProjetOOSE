package Facade;

import BuisnessLogic.Chat.AbstractChat;

public interface IChatFacade {

    boolean addChat(AbstractChat chat);
    boolean deleteChat(AbstractChat chat);
    boolean updateChat(AbstractChat chat);
    boolean getChatByProjectId(int idProject);
}
