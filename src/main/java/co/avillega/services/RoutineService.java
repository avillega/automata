package co.avillega.services;

import co.avillega.entities.Command;
import co.avillega.entities.Routine;
import co.avillega.repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoutineService {

    @Autowired
    private RoutineRepository routineRepository;

    public RoutineService() {
    }

    public List<Command> getCommandsByRoutineId(long id) {
        return routineRepository.findOne(id).getCommands();
    }

    public List<Routine> getRoutines() {
        List<Routine> ret = new ArrayList<>();
        for (Routine routine : routineRepository.findAll()) {
            ret.add(routine);
        }
        return ret;
    }

    public void setCommandsToRoutine(long id, List<Command> commands) {
        Routine routine = routineRepository.findOne(id);
        routine.setCommands(commands);
        routineRepository.save(routine);
    }

    public void addRoutine(Routine routine) {
        routineRepository.save(routine);
    }

    public void deleteRoutine(long id) {
        routineRepository.delete(id);
    }


}
