package com.example.demo.services;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public CustomUserDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findUsersByEmail(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
        return buidl(user);
    }

    public Users loadUserById(Long id){
        return usersRepository.findUsersById(id).orElse(null);
    }

    public static Users buidl(Users users){
        List<GrantedAuthority> authorities = users.getRole().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());

        return new Users(users.getId(), users.getUsername(), users.getEmail(), users.getPassword(), authorities);
    }

}
