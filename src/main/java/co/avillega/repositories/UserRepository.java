package co.avillega.repositories;

import co.avillega.entities.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Andres Villegas on 2017-04-19.
 */
public interface UserRepository extends MongoRepository<AppUser, String> {
    AppUser findFirstByUserName(String userName);

}
