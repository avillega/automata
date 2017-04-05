package co.avillega.entities;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.annotation.Transient;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@Configurable
public class Command implements Serializable {

    @Transient
    private RestTemplate restTemplate = new RestTemplate();

    private Instruction instruction;
    private long param;

    public Command(){}

    public Command(String instruction, long param) {
        this.instruction = Instruction.valueOf(instruction);
        this.param = param;
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

    public void execute() {
        //restTemplate.getForEntity(String.format("http://192.168.100.12/mailbox/%s:%d", instruction.name(), param), String.class);
        System.out.println(String.format(toString()));
    }

    @Override
    public String toString() {
        return String.format("%s:%d", instruction.name(), param);
    }
}
