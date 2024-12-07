package com.fahim.shoppingcard.repository;

import com.fahim.shoppingcard.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser,Long> {


    MyUser findByEmail(String email);
}
