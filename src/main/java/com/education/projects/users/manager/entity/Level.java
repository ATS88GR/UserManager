package com.education.projects.users.manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
