package com.fahim.shoppingcard.admin.model;

import com.fahim.shoppingcard.admin.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    private String first_name;

    private String last_name;

    private String email;

    private String password;

    private UserRole userRole;


}
