package com.education.projects.users.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    public User() {
        if (createdAt == null)
            this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        else
            this.modificationAt = Timestamp.valueOf(LocalDateTime.now());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "User id", example = "1")
    @Column(name = "id", insertable = false)
    private Integer id;

    @Schema (name = "firstName", description = "User firstname", example = "John")
    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Schema (name = "lastName", description = "User lastname", example = "Smith")
    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Schema (name = "password", description = "User password", example = "Gib5!?jEs#")
    @Column(name = "password", nullable = false)
    private String password;

    @Schema (name = "email", description = "User email", example = "abcdefg@gmail.com")
    @Column(name = "email", nullable = false)
    private String email;

    @Schema (name = "phone", description = "User phone number", example = "+375334455667")
    @Column(name = "phone", nullable = false)
    private String phone;

    /*@Schema (name = "roleId", description = "User role", example = "2")
    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Schema (name = "levelId", description = "User level", example = "3")
    @Column(name = "level_id", nullable = false)
    private Integer levelId;*/

    @Schema (name = "createdAt", description = "User creation date", example = "2014-04-28T16:25:49.341")
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Schema (name = "modificationAt", description = "User modification date", example = "2014-04-28T16:25:49.341")
    @Column(name = "modification_at", nullable = true)
    private Timestamp modificationAt;

    @OneToOne(cascade = CascadeType.ALL)
    @Schema (name = "roleId", description = "User role", example = "2")
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @Schema (name = "levelId", description = "User level", example = "3")
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    private Level level;
}
