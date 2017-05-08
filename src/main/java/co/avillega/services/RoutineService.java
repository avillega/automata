package co.avillega.services;

import co.avillega.entities.AppUser;
import co.avillega.entities.Command;
import co.avillega.entities.Routine;
import co.avillega.repositories.RoutineRepository;
import co.avillega.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final UserRepository userRepository;
    private final ConveyorService conveyorService;

    @Autowired
    public RoutineService(RoutineRepository routineRepository, UserRepository userRepository, ConveyorService conveyorService) {
        this.routineRepository = routineRepository;
        this.userRepository = userRepository;
        this.conveyorService = conveyorService;
    }

    public List<Command> getCommandsByRoutineId(String id) {
        return routineRepository.findOne(id).getCommands();
    }

    public List<Routine> getRoutines() {
        return routineRepository.findAll();
    }

    public Routine setCommandsToRoutine(String id, List<Command> commands) {
        Routine routine = routineRepository.findOne(id);
        routine.setCommands(commands);
        routineRepository.save(routine);
        return routine;
    }

    public void addRoutine(Routine routine) {
        routineRepository.save(routine);
    }

    public Routine getRoutine(String id) {
        return routineRepository.findOne(id);
    }

    public void deleteRoutine(String id) {
        routineRepository.delete(id);
    }

    public void startRoutine(String id) {
        Routine routine = routineRepository.findOne(id);
        executeRoutine(routine);
    }

    private void executeRoutine(Routine routine) {
        for (Command command : routine.getCommands()) {
            conveyorService.genericCommand(command);
        }
        //Stop Implicito
        conveyorService.stop();
    }

    public void emergencyStop() {
        conveyorService.emergencyStop();
    }

    public List<Routine> getRoutinesByUserName(String userName) {
        AppUser user = userRepository.findFirstByUserName(userName);
        return routineRepository.findByOwnerId(user.getId());
    }


}
