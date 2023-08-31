package com.education.projects.users.manager.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "UserRoles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(name = "id", description = "Role id", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    @Column(name = "id", insertable = false)
    private UUID id;

    @Schema(name = "roleDescr", description = "Description of the role", example = "administrator")
    @Column(name = "roledescr", nullable = false)
    private String roleDescr;
}
