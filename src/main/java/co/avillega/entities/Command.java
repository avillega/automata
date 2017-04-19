package co.avillega.entities;

import java.io.Serializable;


public class Command implements Serializable {

    private Instruction instruction;
    private double param;

    public Command(){}

    public Command(String instruction, double param) {
        this.instruction = Instruction.valueOf(instruction);
        this.param = param;
    }


    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public double getParam() {
        return param;
    }

    public void setParam(double param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return String.format("%s:%.2f", instruction.name(), param);
    }

    public String pretty() {
        if (this.instruction == Instruction.SPEED || this.instruction == Instruction.WAIT)
            return String.format("%s %.2f", instruction.toString(), param);
        else
            return instruction.toString();
    }
}
