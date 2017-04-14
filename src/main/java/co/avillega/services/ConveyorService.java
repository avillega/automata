package co.avillega.services;

import co.avillega.entities.Command;
import co.avillega.entities.Instruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class ConveyorService {
    private static final Logger logger = LoggerFactory.getLogger(ConveyorService.class);
    private RestTemplate restTemplate = new RestTemplate();


    public String changeSpeed(double speed){

        //restTemplate.getForEntity(String.format("http://192.168.100.12/mailbox/%d", (int) speed), String.class);
        logger.info(String.format("Speed Set to %f", speed));
        return speed+"";
    }

    public String stop() {
        logger.info("stopped");
        return "stopped";
    }

    public String emergencyStop(){
        logger.warn("Emergency stop activated");
        return "Emergency stop activated";
    }

    public String start() {
        logger.info("started");
        return "Started";
    }

    public String waitInst(long seconds) {
        logger.info(String.format("wainting %d seconds", seconds));
        return String.format("wainting %d seconds", seconds);
    }

    public void genericCommand(Command command) {
        if (command.getInstruction() == Instruction.SPEED) {
            changeSpeed(command.getParam());
        } else if (command.getInstruction() == Instruction.START) {
            start();
        } else if (command.getInstruction() == Instruction.STOP) {
            stop();
        } else if (command.getInstruction() == Instruction.WAIT) {
            waitInst(command.getParam());
        }
    }


}
