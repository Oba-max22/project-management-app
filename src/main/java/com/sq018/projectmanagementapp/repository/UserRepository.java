package com.sq018.projectmanagementapp.repository;

import com.sq018.projectmanagementapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);
}
