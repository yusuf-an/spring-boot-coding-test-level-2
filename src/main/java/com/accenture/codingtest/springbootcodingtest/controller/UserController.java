package com.accenture.codingtest.springbootcodingtest.controller;
import com.accenture.codingtest.springbootcodingtest.mappers.UserMapper;
import com.accenture.codingtest.springbootcodingtest.model.UserDto;
import com.accenture.codingtest.springbootcodingtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll().stream().map(m->userMapper.convertUserEntityToUserDto(m)).collect(Collectors.toList());
    }

    @GetMapping(value = "/{userId}")
    public UserDto findByUserId(@PathVariable String userId) {
        return userService.findByUserId(userId).map(m->userMapper.convertUserEntityToUserDto(m))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found for userId:"+userId));
    }

    @PostMapping()
    public UserDto create(@RequestBody UserDto userDto) {
        return userMapper.convertUserEntityToUserDto(userService.save(userMapper.convertUserDtoToUserEntity(userDto)));
    }

    @PutMapping("/{userId}")
    public UserDto updateIdempotent(@RequestBody UserDto userDto, @PathVariable String userId) {
        return userMapper.convertUserEntityToUserDto(userService.update(userMapper.convertUserDtoToUserEntity(userDto), userId));
    }

    @PatchMapping("/{userId}")
    public UserDto update(@RequestBody UserDto userDto, @PathVariable String userId) {
        return userMapper.convertUserEntityToUserDto(userService.update(userMapper.convertUserDtoToUserEntity(userDto), userId));
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable String userId) {
        userService.delete(userId);
    }




}