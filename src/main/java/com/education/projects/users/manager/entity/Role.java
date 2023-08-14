package com.education.projects.users.manager.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "UserRoles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Role id", example = "1")
    @Column(name = "id", insertable = false)
    private Integer id;

    @Schema (name = "role", description = "Description of the role", example = "administrator")
    @Column(name = "role", nullable = false)
    private String role;

    @OneToOne(mappedBy = "role")
    private User user;
}
