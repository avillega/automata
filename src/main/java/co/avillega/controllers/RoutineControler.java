package co.avillega.controllers;

import co.avillega.entities.Routine;
import co.avillega.services.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Andres Villegas on 2017-03-28.
 */
@RestController
public class RoutineControler {
    @Autowired
    private RoutineService routineService;

    public Routine addRoutine(Routine routine) {
        routineService.addRoutine(routine);
        return routine;
    }

    public List<Routine> getRoutines() {
        return routineService.getRoutines();
    }

    public void deleteRoutine(long id) {
        routineService.deleteRoutine(id);
    }

}
