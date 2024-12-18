package com.zql.spring_redis_cache.service;

import com.zql.spring_redis_cache.entity.User;
import com.zql.spring_redis_cache.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id){
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()) return byId.get();
        throw new RuntimeException("Do no exist user with this ID");
    }

    @CachePut(value = "users", key = "#result.getId()")
    @Transactional
    public User saveUser(User user){
        User savedUser = userRepository.save(user);
        System.out.println("Saved User: " + savedUser);
        return savedUser;
    }

    @CacheEvict(value = "users", key = "#id")
    @Transactional
    public void deleteUser(Long id){
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()){
            userRepository.deleteById(id);
            return;
        }
        throw new RuntimeException("Do no exist user with this ID");
    }


}

