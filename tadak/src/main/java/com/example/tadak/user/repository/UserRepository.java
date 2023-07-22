package com.example.tadak.user.repository;

import com.example.tadak.user.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findBySocialIdAndSocialType(String socialId, String socialType);

}
