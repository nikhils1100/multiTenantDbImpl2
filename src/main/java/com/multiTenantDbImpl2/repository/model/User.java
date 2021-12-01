package com.multiTenantDbImpl2.repository.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "User")
public class User implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="user_id")
    public int userId;
    @Column(name="username")
    public String username;
}
