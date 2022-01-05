package com.demospringsecurity.DAO;

import com.demospringsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserDAO extends JpaRepository<User, String> {
}
