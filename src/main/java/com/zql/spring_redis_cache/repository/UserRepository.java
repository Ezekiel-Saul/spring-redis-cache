package com.zql.spring_redis_cache.repository;

import com.zql.spring_redis_cache.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
