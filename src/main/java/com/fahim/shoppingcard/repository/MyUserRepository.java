package com.fahim.shoppingcard.repository;

import com.fahim.shoppingcard.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser,Long> {


    MyUser findByEmail(String email);
}
