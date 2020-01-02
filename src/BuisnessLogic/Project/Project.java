package BuisnessLogic.Project;


import java.time.LocalDate;


public class Project extends AbstractProject {
    private int id;
    private String name;
    private String description;
    private LocalDate dateCreation;
    private boolean isAgile;

    public Project(int id, String name, String description, LocalDate dateCreation, boolean isAgile) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreation = dateCreation;
        this.isAgile = isAgile;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public LocalDate getDateCreation() {
        return dateCreation;
    }

    @Override
    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public boolean isAgile() {
        return isAgile;
    }

    @Override
    public void setAgile(boolean agile) {
        isAgile = agile;
    }
}
