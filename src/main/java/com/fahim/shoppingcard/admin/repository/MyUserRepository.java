package com.fahim.shoppingcard.admin.repository;

import com.fahim.shoppingcard.admin.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser,Long> {


    MyUser findByEmail(String email);
}
