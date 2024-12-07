package com.fahim.shoppingcard.services.userdetails;

import com.fahim.shoppingcard.model.MyUser;
import com.fahim.shoppingcard.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserService implements IMyUserService{

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * This Constructor Injection
     * */
    private final MyUserRepository myUserRepository;
    public MyUserService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public MyUser addNewUser(MyUser myUser) {
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        return myUserRepository.save(myUser);
    }
}
