package com.education.projects.users.manager.entity.Level;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", insertable = false)
    private UUID id;

    @Column(name = "leveldescr", nullable = false)
    private String levelDescr;
}
