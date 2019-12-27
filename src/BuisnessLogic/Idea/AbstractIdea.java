package BuisnessLogic.Idea;

import BuisnessLogic.User.AbstractUser;

public abstract class AbstractIdea {

    public abstract int getId();

    public abstract String getName();

    public abstract AbstractUser getCreator();

    public abstract String getDescription();

    public abstract String getSubject();

    public abstract String getState();


    public abstract void setId(int id);

    public abstract void setName(String name);

    public abstract void setCreator(AbstractUser creator);

    public abstract void setDescription(String description);

    public abstract void setSubject(String subject);

    //"validated" or "refused"
    public abstract void setState(String state);


    public abstract void refuse();

    public abstract void validate();
}
