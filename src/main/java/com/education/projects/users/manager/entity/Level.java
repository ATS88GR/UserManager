package com.education.projects.users.manager.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Level id", example = "1")
    @Column(name = "id", insertable = false)
    private Integer id;

    @Schema (name = "levelDescr", description = "Description of the level", example = "professor")
    @Column(name = "leveldescr", nullable = false)
    private String levelDescr;
}
