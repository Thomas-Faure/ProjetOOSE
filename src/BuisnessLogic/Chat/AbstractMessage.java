package BuisnessLogic.Chat;

import BuisnessLogic.User.GlobalUser;

public abstract class AbstractMessage {

    public abstract int getIdMessage();

    public abstract String getContenu();

    public abstract void setContenu(String contenu);

    public abstract int getIdChat();

    public abstract GlobalUser getCreateur();
}
