package com.education.projects.users.manager.repository;

import com.education.projects.users.manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
   /* @Query("select")
    Collection <User> findAllByBrand();*/
}
