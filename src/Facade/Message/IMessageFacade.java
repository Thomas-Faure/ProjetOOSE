package Facade.Message;

import BuisnessLogic.Message.AbstractMessage;

import java.util.List;

public interface IMessageFacade {

    boolean addMessage(AbstractMessage message);
    boolean deleteMessage(AbstractMessage message);
    //AbstractMessage getMessageById(int idProject);
    boolean getAllMessageByChat(int idChat);
    
}
