package com.education.projects.users.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(name = "id", description = "User id", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    @Column(name = "id", insertable = false)
    private UUID id;

    @Schema(name = "firstName", description = "User firstname", example = "John")
    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Schema(name = "lastName", description = "User lastname", example = "Smith")
    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Schema(name = "password", description = "User password", example = "Gib5!?jEs#")
    @Column(name = "password", nullable = false)
    private String password;

    @Schema(name = "email", description = "User email", example = "abcdefg@gmail.com")
    @Column(name = "email", nullable = false)
    private String email;

    @Schema(name = "phone", description = "User phone number", example = "+375334455667")
    @Column(name = "phone", nullable = false)
    private String phone;

    @CreationTimestamp
    @Schema(name = "createdAt", description = "User creation date", example = "2014-04-28T16:25:49.341")
    @Column(name = "created_at", nullable = false, updatable=false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Schema(name = "modificationAt", description = "User modification date", example = "2014-04-28T16:25:49.341")
    @Column(name = "modification_at")
    private Timestamp modificationAt;

    @OneToOne(cascade = CascadeType.ALL)
    @Schema(name = "roleId", description = "User role", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @Schema(name = "levelId", description = "User level", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    private Level level;
}
