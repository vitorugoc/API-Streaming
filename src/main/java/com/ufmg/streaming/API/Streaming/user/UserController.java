package com.ufmg.streaming.API.Streaming.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        User user = userService.getUserByEmail(email);

        if(user != null){
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{idUser}")
    public ResponseEntity<User> editUser(@PathVariable Integer idUser, @RequestBody User updatedUser){
        User existingUser = userService.getUserById(idUser);

        if(existingUser == null){
            return ResponseEntity.notFound().build();
        }

        existingUser.setFirstname(updatedUser.getFirstname());
        existingUser.setLastname(updatedUser.getLastname());
        existingUser.setIs_child_user(updatedUser.getIs_child_user());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setContracted_plan(updatedUser.getContracted_plan());

        User newUpdatedUser = userService.saveUser(existingUser);

        return ResponseEntity.ok(newUpdatedUser);
    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer idUser){
        User existingUser = userService.getUserById(idUser);

        if(existingUser == null){
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(existingUser);

        return ResponseEntity.ok("Usuário deletado com sucesso!");
    }
}
