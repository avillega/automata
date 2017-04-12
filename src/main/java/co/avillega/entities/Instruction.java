package co.avillega.entities;

/**
 * Created by Andres Villegas on 2017-03-23.
 *
 */
public enum Instruction {
    START("START"),
    SPEED("SET_SPEED"),
    WAIT("WAIT"),
    STOP("STOP"),
    EMERGENCY("EMERGENCY");

    private String string;

    Instruction(String string) {
        this.string = string;
    }

    public String toString() {
        return string;
    }


}
