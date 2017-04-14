package co.avillega.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document(collection = "routines")
public class Routine {

    @Id
    private String id;
    private String description;
    private String name;

    private List<Command> commands;

    public Routine(String description, String name) {
        this.description = description;
        this.name = name;
        this.commands = new ArrayList<>();

    }

    public Routine() {
        this.commands = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
                ", commands=" + Arrays.toString(commands.toArray()) +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Routine routine = (Routine) o;

        return id.equals(routine.id) && (description != null ? description.equals(routine.description) :
                routine.description == null) && (name != null ? name.equals(routine.name) : routine.name == null);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
