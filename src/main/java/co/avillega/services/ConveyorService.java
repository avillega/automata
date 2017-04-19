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
    private static final String ARDUINO = "192.168.100.12";
    private RestTemplate restTemplate = new RestTemplate();

    public String changeSpeed(double speed){
        restTemplate.getForEntity(String.format("http://%s/mailbox/speed:%f", ARDUINO, speed), String.class);
        logger.info(String.format("Speed Set to %f", speed));
        return speed+"";
    }

    public String stop() {
        restTemplate.getForEntity(String.format("http://%s/mailbox/stop", ARDUINO), String.class);
        logger.info("stopped");
        return "stopped";
    }

    public String emergencyStop(){
        restTemplate.getForEntity(String.format("http://%s/arduino/emergency", ARDUINO), String.class);
        logger.warn("Emergency stop activated");
        return "Emergency stop activated";
    }

    public String start() {
        restTemplate.getForEntity(String.format("http://%s/mailbox/start", ARDUINO), String.class);
        logger.info("started");
        return "Started";
    }

    public String waitInst(double seconds) {
        seconds *= 1000;
        restTemplate.getForEntity(String.format("http://%s/mailbox/wait:%f", ARDUINO, seconds), String.class);
        logger.info(String.format("wainting %f seconds", seconds / 1000));
        return String.format("wainting %f seconds", seconds / 1000);
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
