package com.fahim.shoppingcard.admin.controllers;

import com.fahim.shoppingcard.admin.model.LoginForm;
import com.fahim.shoppingcard.admin.model.MyUser;
import com.fahim.shoppingcard.admin.response.ApiResponse;
import com.fahim.shoppingcard.admin.services.userdetails.JwtService;
import com.fahim.shoppingcard.admin.services.userdetails.MyUserDetailsService;
import com.fahim.shoppingcard.admin.services.userdetails.MyUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@AllArgsConstructor
public class RegistrationAuthenticationController {

    @Autowired
    private MyUserService myUserService;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager manager;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    /**
     * We need to add new User bye: myUserService,
     * */
    @PostMapping("/registration")
    public ResponseEntity<ApiResponse> addNewUser(@RequestBody MyUser myUser){
        try{
            MyUser addedNewUser = myUserService.addNewUser(myUser);
            return ResponseEntity.ok().body(new ApiResponse("Added new user",addedNewUser));
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }


    @PostMapping("/authenticate")
    public String authenticate(@RequestBody LoginForm loginForm){
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(
           loginForm.email(),
            loginForm.password()
        ));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(myUserDetailsService.loadUserByUsername(loginForm.email()));
        }else{
            throw  new UsernameNotFoundException("NOT FOUND");
        }

    }

}
