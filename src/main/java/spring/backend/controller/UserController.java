package spring.backend.controller;

import spring.backend.dto.UserDto;
import spring.backend.entity.User;
import spring.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Build Post User REST API
    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Build Get User REST API
    @GetMapping("/find/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long uid) {
        UserDto userDto = userService.getUserById(uid);
        return ResponseEntity.ok(userDto);
    }

    // Build Get All Users REST API
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Build Put User REST API
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long uid, @RequestBody UserDto updatedUser){
        UserDto userDto = userService.updateUser(uid, updatedUser);
        return ResponseEntity.ok(userDto);
    }

    // Build Delete User REST API
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long uid) {
        userService.deleteUser(uid);
        return ResponseEntity.ok().body("{\"message\": \"User Deleted Successful\"}");
    }
}
