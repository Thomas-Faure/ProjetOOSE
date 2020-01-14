package BusinessLogic.Project;


import java.time.LocalDate;

/**
 * Cette classe représente un objet de type projet
 *
 * @author Rémi Salmi
 *
 */

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

    /**
     * Retourne l'id d'un projet
     * @author Rémi Salmi
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * set l'id d'un projet
     * @author Rémi Salmi
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne le nom d'un projet
     * @author Rémi Salmi
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * set le nom d'un projet
     * @author Rémi Salmi
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne la description d'un projet
     * @author Rémi Salmi
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Set la description d'un projet
     * @author Rémi Salmi
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retourne la date de création du projet
     * @author Rémi Salmi
     */
    @Override
    public LocalDate getDateCreation() {
        return dateCreation;
    }

    /**
     * Set la date de création du projet
     * @author Rémi Salmi
     */
    @Override
    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Dis si le projet est agile
     * @author Rémi Salmi
     */
    @Override
    public boolean isAgile() {
        return isAgile;
    }

    /**
     *Set l'agilité d'un projet
     * @author Rémi Salmi
     */
    @Override
    public void setAgile(boolean agile) {
        isAgile = agile;
    }
}
