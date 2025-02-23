package gr.aueb.mvcfilmbro.service;

import gr.aueb.mvcfilmbro.data.UserRepository;
import gr.aueb.mvcfilmbro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public User authenticate(String username, String password) {
        return userRepository.authentication(username, password);
    }
}
