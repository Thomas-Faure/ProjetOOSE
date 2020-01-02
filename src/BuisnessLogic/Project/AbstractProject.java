package BuisnessLogic.Project;

import java.time.LocalDate;

public abstract class AbstractProject {
    public abstract int getId();
    public abstract void setId(int id);
    public abstract String getName();
    public abstract void setName(String name);
    public abstract String getDescription();
    public abstract void setDescription(String description);
    public abstract LocalDate getDateCreation();
    public abstract void setDateCreation(LocalDate dateCreation);
    public abstract boolean isAgile();
    public abstract void setAgile(boolean agile);
}
