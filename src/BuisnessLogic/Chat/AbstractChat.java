package BuisnessLogic.Chat;

import BuisnessLogic.Message.AbstractMessage;

import java.util.List;

public abstract class AbstractChat {





    public abstract int getIdChat();

    public abstract String getChatName();

    public abstract void setChatName(String chatName);

    public abstract int getIdProject();

    public abstract void setIdProject();

    public abstract List<AbstractMessage> getHistoriqueMessage();

    public abstract void setHistoriqueMessage(List<AbstractMessage> historiqueMessage);
}
