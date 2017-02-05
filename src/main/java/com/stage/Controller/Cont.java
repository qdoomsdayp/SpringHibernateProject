package com.stage.Controller;

import com.google.gson.Gson;
import com.stage.model.Role;
import com.stage.model.User;
import com.stage.service.RoleService;
import com.stage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "/")
public class Cont {
    @Autowired
     UserService userService;
    @Autowired
    RoleService roleService;

@RequestMapping(value = "user",method = RequestMethod.GET)
    public String start(Model model){
    model.addAttribute("listUsers",new Gson().toJson(userService.listUser()));
    model.addAttribute("listRoles",new Gson().toJson(roleService.listRole()));
return "users";
}
    @RequestMapping(value = "roles", method = RequestMethod.GET)
    public String Role(Model model) {
        model.addAttribute("listRolesR", new Gson().toJson(roleService.listRole()));
        return "roles";
    }

    @RequestMapping(value = "addU", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<List<User>> addUser(@RequestBody User us)  {

        System.out.println("ADD - OK");
        this.userService.addUser(us);
        return new ResponseEntity<>(this.userService.listUser(), HttpStatus.OK);
    }
    @RequestMapping(value = "editU", method = RequestMethod.POST)
    public  @ResponseBody  ResponseEntity<List<User>> editUser(@RequestBody User us)  {

        System.out.println("EDIR - OK");
        this.userService.updateUser(us);
        return new ResponseEntity<>(this.userService.listUser(), HttpStatus.OK);
    }
    @RequestMapping(value = "deleteU", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<List<User>> delUser(@RequestBody User us){

        System.out.println("DEL - OK");
        this.userService.removeUser(us.getIdU());
        return new ResponseEntity<>(this.userService.listUser(), HttpStatus.OK);
    }



    @RequestMapping(value = "roles/select", method = RequestMethod.POST)
    public  @ResponseBody ResponseEntity<List<Role>> selectRole(@RequestBody Role role){

        System.out.println("SELECT R - OK");

        return new ResponseEntity<>(this.roleService.listRole(), HttpStatus.OK);
    }

    @RequestMapping(value = "roles/delete", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<List<Role>> delRole(@RequestBody Role role) {

        System.out.println("DEL R - OK");
        this.roleService.removeRole(role.getIdR());
        return new ResponseEntity<>(this.roleService.listRole(), HttpStatus.OK);
    }

    @RequestMapping(value = "roles/edit", method = RequestMethod.POST)
    public@ResponseBody  ResponseEntity<List<Role>> editRole(@RequestBody Role role) {

        System.out.println("EDIT R - OK");
        this.roleService.updateRole(role);
        return new ResponseEntity<>(this.roleService.listRole(), HttpStatus.OK);
    }

    @RequestMapping(value = "roles/addR", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<List<Role>> addRole(@RequestBody Role role) {

        System.out.println("ADD R - OK");
        this.roleService.addRole(role);
        return new ResponseEntity<>(this.roleService.listRole(), HttpStatus.OK);
    }


}