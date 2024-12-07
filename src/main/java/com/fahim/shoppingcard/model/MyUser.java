package com.fahim.shoppingcard.model;

import com.fahim.shoppingcard.enums.UserRole;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private UserRole userRole;


}
