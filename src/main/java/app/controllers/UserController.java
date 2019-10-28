package app.controllers;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.listUsers());
        return "user";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if(user.getId() == null)
            userService.addUser(user);
        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @ResponseBody
    @RequestMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") String id) {
        User user = userService.getUserById(id);
        if(user != null){
            return user;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/getUserByLogin")
    public User getUserByEmail(@RequestParam("login") String login) {
        User user = userService.getUserByLogin(login);
        if(user != null){
            return user;
        }
        return null;
    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") String id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listUsers", userService.listUsers());
        return "user";
    }
}
