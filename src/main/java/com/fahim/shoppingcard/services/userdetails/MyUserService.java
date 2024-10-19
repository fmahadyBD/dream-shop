package com.fahim.shoppingcard.services.userdetails;

import com.fahim.shoppingcard.model.MyUser;
import com.fahim.shoppingcard.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyUserService implements IMyUserService{

    private final MyUserRepository myUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public MyUserService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public MyUser addNewUser(MyUser myUser) {

        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        return myUserRepository.save(myUser);
    }
}
