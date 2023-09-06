package com.education.projects.users.manager.entity.Level;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "user_levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(name = "id", description = "Level id", example = "086d792e-7974-4fe4-b2e0-2dba9f79bed8")
    @Column(name = "id", insertable = false)
    private UUID id;

    @Schema(name = "levelDescr", description = "Description of the user level", example = "phd")
    @Column(name = "leveldescr", nullable = false)
    private String levelDescr;
}
