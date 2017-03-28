package co.avillega.entities;

import javax.persistence.*;

/**
 * Created by Andres Villegas on 2017-03-23.
 *
 */
@Entity
public class Command {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Enumerated(EnumType.STRING)
    private Instruction instruction;
    private long param;

    public Command(){}

    public Command(Instruction instruction, long param) {
        this.instruction = instruction;
        this.param = param;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public long getParam() {
        return param;
    }

    public void setParam(long param) {
        this.param = param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;

        if (id != command.id) return false;
        if (param != command.param) return false;
        return instruction == command.instruction;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (instruction != null ? instruction.hashCode() : 0);
        result = 31 * result + (int) (param ^ (param >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s:%d", instruction.name(), param);
    }
}
