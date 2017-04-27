package co.avillega.services;

import co.avillega.entities.AppUser;
import co.avillega.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    public AppUser authenticateUser(String userName, String enteredPassword) {

        AppUser user = userRepository.findFirstByUserName(userName);
        if (user == null) return null;
        if (encoder.matches(enteredPassword, user.getPasswordHash()))
            return user;
        else
            return null;

    }

    public AppUser getUser(String userName) {
        return userRepository.findFirstByUserName(userName);
    }

    public AppUser createUser(String userName, String password) {
        AppUser user = userRepository.findFirstByUserName(userName);
        if (user != null) return null;
        String encodedPass = encoder.encode(password);
        user = new AppUser(userName, encodedPass);
        user = userRepository.save(user);
        return user;
    }

}
