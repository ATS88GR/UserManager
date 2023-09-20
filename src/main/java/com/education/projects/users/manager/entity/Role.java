package com.education.projects.users.manager.entity;

import jakarta.persistence.*;
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
    @Column(name = "id", insertable = false)
    private UUID id;

    @Column(name = "roledescr", nullable = false)
    private String roleDescr;
}
