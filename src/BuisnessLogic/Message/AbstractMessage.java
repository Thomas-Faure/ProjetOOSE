package BuisnessLogic.Message;

import BuisnessLogic.User.AbstractUser;

public abstract class AbstractMessage {

    public abstract int getIdMessage();

    public abstract String getContenu();

    public abstract void setContenu(String contenu);

    public abstract int getIdChat();

    public abstract AbstractUser getCreateur();
}
