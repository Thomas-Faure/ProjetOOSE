package BuisnessLogic.Task;

import BuisnessLogic.User.User;

import java.time.LocalDate;

public abstract class AbstractTask {
    public abstract int getId();

    public abstract String getName();

    public abstract int getPriority();

    public abstract LocalDate getDeadline();

    public abstract User getCreator();
    public abstract String getDescription();


}
