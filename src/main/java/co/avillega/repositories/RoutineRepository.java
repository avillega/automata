package co.avillega.repositories;

import co.avillega.entities.Routine;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Andres Villegas on 2017-03-28.
 */
public interface RoutineRepository extends CrudRepository<Routine, Long> {
}
