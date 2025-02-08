package spring.backend.service;

import spring.backend.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long uid);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long uid, UserDto updatedUser);

    void deleteUser(Long uid);
}
