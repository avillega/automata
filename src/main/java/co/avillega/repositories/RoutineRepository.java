package co.avillega.repositories;

import co.avillega.entities.Routine;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * Created by Andres Villegas on 2017-03-28.
 */
public interface RoutineRepository extends MongoRepository<Routine, String> {
    List<Routine> findByOwnerId(String ownerId);
}
