package app.service;

import app.dao.UserRepository;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static final String patternForNameAndSurname = "[a-zA-Z]{2,10}";
    private static final String patternForLogin = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    @Transactional
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        if (validateDataFromUser(user))
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


    @Override
    @Transactional
    public User getUserById(String id) {
        return userRepository.getOne(id);
    }


    @Override
    @Transactional
    public User getUserByLogin(String login) {
        return (User) userRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public boolean validateDataFromUser(User user) {
        if (!user.getName().matches(patternForNameAndSurname))
            return false;
        if (!user.getSurname().matches(patternForNameAndSurname))
            return false;
        if (!user.getLogin().matches(patternForLogin))
            return false;
        return true;
    }
}
