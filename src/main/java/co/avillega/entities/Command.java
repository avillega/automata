package co.avillega.entities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by Andres Villegas on 2017-03-23.
 */
public class Command {

    @Enumerated(EnumType.STRING)
    private Instruction instruction;
    private long param;

    public Command(){}

    public Command(Instruction instruction, long param){

    }




}
