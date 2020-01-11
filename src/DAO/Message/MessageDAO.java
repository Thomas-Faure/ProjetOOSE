package DAO.Message;

import BuisnessLogic.Message.AbstractMessage;

import java.util.List;

public interface MessageDAO {

    boolean save(AbstractMessage message);
    boolean delete(int idMessage);
    AbstractMessage getMessageById(int idMessage);
    List<AbstractMessage> getAllMessageByChat(int idChat);
}
