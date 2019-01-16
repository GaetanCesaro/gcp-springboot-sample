package com.gegette.springgcp.service;

import com.gegette.springgcp.model.GroupDB;
import com.gegette.springgcp.model.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserRepositoryService {
    @Autowired
    private IUserRepository userRepository;

    @Transactional
    public void createAndSaveUserRelationshipsInTransaction(UserDB user, List<GroupDB> groups) {
        user.setGroups(groups);
        this.userRepository.save(user);

        System.out.println("Relationship links were saved between a singer, bands, and instruments in a single transaction: " + user);
    }
}
