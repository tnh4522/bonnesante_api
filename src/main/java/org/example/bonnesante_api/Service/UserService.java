package org.example.bonnesante_api.Service;

import org.example.bonnesante_api.Entity.UserEntity;

public interface UserService {
    UserEntity findByUsernameAndPassword(String username, String password);

    UserEntity saveUser(UserEntity user);

    UserEntity getUserByID(long id);
}
