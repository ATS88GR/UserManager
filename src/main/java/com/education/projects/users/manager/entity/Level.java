package com.education.projects.users.manager.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "UserLevels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Level id", example = "1")
    @Column(name = "id", insertable = false)
    private Integer id;

    @Schema (name = "level", description = "Description of the level", example = "professor")
    @Column(name = "level", nullable = false)
    private String level;

    @OneToOne(mappedBy = "level")
    private User user;
}
