package co.avillega.services;

import co.avillega.entities.Routine;
import co.avillega.repositories.RoutineRepository;
import co.avillega.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final RoutineRepository routineRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserService(RoutineRepository routineRepository, UserRepository userRepository) {
        this.routineRepository = routineRepository;
        this.userRepository = userRepository;
    }

    public List<Routine> getUserRoutines() {
        return null;
    }
}
