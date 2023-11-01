package com.Security.Security.serviceImpl;

import com.Security.Security.entity.Users;
import com.Security.Security.payload.UserDto;
import com.Security.Security.repository.UserRepository;
import com.Security.Security.service.UserService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Data
@Setter
@Getter
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        Users user = modelMapper.map(userDto, Users.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Users savedUser = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }
}
