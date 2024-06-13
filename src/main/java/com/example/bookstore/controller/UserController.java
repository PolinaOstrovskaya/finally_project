package com.example.bookstore.controller;


import com.example.bookstore.exception.CustomValidException;
import com.example.bookstore.model.User;
import com.example.bookstore.model.dto.UserCreateDto;
import com.example.bookstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    @Operation
    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "500"),
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable("id") @Parameter Long id) {
        log.info("IN getUserById ");
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid UserCreateDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomValidException(bindingResult.getAllErrors().toString());
        }
        return new ResponseEntity<>(userService.createUser(user) ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomValidException(bindingResult.getAllErrors().toString());
        }
        return new ResponseEntity<>(userService.updateUser(user) ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.deleteUserById(id) ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }
}