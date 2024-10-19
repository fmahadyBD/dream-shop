package com.fahim.shoppingcard.services.userdetails;
import com.fahim.shoppingcard.model.MyUser;
import com.fahim.shoppingcard.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private MyUserRepository myUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<MyUser> user = Optional.ofNullable(myUserRepository.findByEmail(username));
        if(user.isPresent()){
            var userObject = user.get();

            return User.builder().username(username)
                    .password(userObject.getPassword())
                    .roles(String.valueOf(userObject.getUserRole())).build();
        }

        return null;
    }
}
