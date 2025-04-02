package com.user.repo;

import com.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>
{
    User findById(int id);

    User findByName(String username);

    String findByEmail(String email);
}
