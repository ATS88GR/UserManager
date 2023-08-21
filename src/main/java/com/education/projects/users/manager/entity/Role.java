package com.education.projects.users.manager.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "UserRoles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Role id", example = "1")
    @Column(name = "id", insertable = false)
    private Integer id;

    @Schema (name = "roleDescr", description = "Description of the role", example = "administrator")
    @Column(name = "roledescr", nullable = false)
    private String roleDescr;
}
