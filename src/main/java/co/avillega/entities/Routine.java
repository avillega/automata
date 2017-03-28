package co.avillega.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andres Villegas on 2017-03-23.
 */
@Entity
public class Routine {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private String name;


    @OneToMany(mappedBy = "routine")
    private List<Command> commands;

    public Routine(String description, String name) {

        this.description = description;
        this.name = name;
        this.commands = new ArrayList<>();

    }

    public Routine() {
        this.commands = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }


    @Override
    public String toString() {
        return "Routine{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", commands=" + commands +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Routine routine = (Routine) o;

        if (id != routine.id) return false;
        if (description != null ? !description.equals(routine.description) : routine.description != null) return false;
        if (name != null ? !name.equals(routine.name) : routine.name != null) return false;
        return commands != null ? commands.equals(routine.commands) : routine.commands == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (commands != null ? commands.hashCode() : 0);
        return result;
    }
}
