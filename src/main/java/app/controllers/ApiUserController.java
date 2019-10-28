package app.controllers;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api")
@Controller
public class ApiUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @ResponseBody
    @GetMapping("/users")
    public List<User> listUsers() {
        return this.userService.listUsers();
    }

    @PostMapping("/user")
    public String addUser(User user, Model model) {
        if(user.getId() == null){
            userService.addUser(user);
            model.addAttribute("user", user);
        }
        return "user";
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
    }

    @ResponseBody
    @GetMapping("/user/{email}")
    public User getUserByEmail(@PathVariable("email") String email) {
        User user = userService.getUserByLogin(email);
        if(user != null){
            return user;
        }
        return null;
    }

    @ResponseBody
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") String id) {
        User user = userService.getUserById(id);
        if(user != null){
            return user;
        }
        return null;
    }
}
