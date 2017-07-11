package co.avillega.services;

import co.avillega.entities.Command;
import co.avillega.entities.Instruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class ConveyorService {
    private static final Logger logger = LoggerFactory.getLogger(ConveyorService.class);

    @Value("${arduino:172.30.251.232}")
    private String ARDUINO;

    private RestTemplate restTemplate = new RestTemplate();


    public String changeSpeed(double speed){
        try {
            int s = (int) (speed * 10);
            s = s > 400 ? 400 : s;
            logger.info(s + "");
            restTemplate.getForEntity(String.format("http://%s/mailbox/speed:%d", ARDUINO, s), String.class);
            logger.info(String.format("Speed Set to %f", speed));
            return speed + "";
        } catch (Exception e) {
            logger.info("Es posible que el arduino este desconectado");
        }
        return "error";
    }

    public String stop() {
        try {
            restTemplate.getForEntity(String.format("http://%s/mailbox/stop", ARDUINO), String.class);
            logger.info(String.format("http://%s/mailbox/stop", ARDUINO));
            logger.info("stopped");
            return "stopped";
        } catch (Exception e) {
            logger.info("Es posible que el arduino este desconectado");
        }
        return "error";

    }

    public String emergencyStop(){
        try {
            restTemplate.getForEntity(String.format("http://%s/arduino/emergency", ARDUINO), String.class);
            logger.warn("Emergency stop activated");
            return "Emergency stop activated";
        } catch (Exception e) {
            logger.info("Es posible que el arduino este desconectado");
        }
        return "error";

    }

    public String start() {
        try {
            restTemplate.getForEntity(String.format("http://%s/mailbox/start", ARDUINO), String.class);
            logger.info("started");
            return "Started";
        } catch (Exception e) {
            logger.info("Es posible que el arduino este desconectado");
        }
        return "error";

    }

    public String waitInst(double seconds) {
        try {
            seconds *= 1000;
            restTemplate.getForEntity(String.format("http://%s/mailbox/wait:%f", ARDUINO, seconds), String.class);
            logger.info(String.format("wainting %f seconds", seconds / 1000));
            return String.format("wainting %f seconds", seconds / 1000);
        } catch (Exception e) {
            logger.info("Es posible que el arduino este desconectado");
        }
        return "error";

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
