package com.Security.Security.secutityService;

import com.Security.Security.entity.Users;
import com.Security.Security.exception.UserNotFound;
import com.Security.Security.repository.UserRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Setter
@Getter
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = null;
        try {
             user = userRepository.findByEmail(email).orElseThrow(
                    ()-> new UserNotFound(String.format("user not found with this email",email))
            );
        } catch (UserNotFound e) {
            throw new RuntimeException(e);
        }
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        return new User(user.getEmail(),user.getPassword(),getAuthorities(roles));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<String> roles) {
        return roles.stream().map(
                role-> new SimpleGrantedAuthority(role)
        ).collect(Collectors.toList());
    }
}
