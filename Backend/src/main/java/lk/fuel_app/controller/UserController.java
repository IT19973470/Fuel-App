package lk.fuel_app.controller;

import lk.fuel_app.entity.AppUser;
import lk.fuel_app.entity.Customer;
import lk.fuel_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "fuel/" + "user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AppUser appUser) {
        return ResponseEntity.ok(userService.login(appUser));
    }
}
