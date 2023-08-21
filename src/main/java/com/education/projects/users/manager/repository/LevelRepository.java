package com.education.projects.users.manager.repository;

import com.education.projects.users.manager.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository <Level, Integer> {
}
