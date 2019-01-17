package com.gegette.springgcp.service.impl;

import com.gegette.springgcp.exception.UserNotFoundException;
import com.gegette.springgcp.model.UserDB;
import com.gegette.springgcp.repository.IUserRepository;
import com.gegette.springgcp.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;

@Slf4j
@Service(value = "UserService")
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDB getUser(String mail) {
        if (mail == null || "".equals(mail)) {
            throw new InvalidParameterException("Le paramètre 'mail' ne doit pas être NULL");
        }

        UserDB userDB = userRepository.findByMail(mail);
        if(userDB == null) {
            throw new UserNotFoundException(String.format("Aucun utilisateur n'a été trouvé pour la recherche %s", mail));
        }

        return userDB;
    }

    @Override
    @Transactional
    public UserDB postUser(UserDB userDB) {
        checkParameters(userDB);

        userDB = userRepository.save(userDB);

        return userDB;
    }

    private void checkParameters(UserDB userDB) {
        if (userDB == null) {
            throw new InvalidParameterException("Le paramètre 'user' ne doit pas être NULL");
        }

        if (userDB.getFirstName() == null) {
            throw new InvalidParameterException("Le paramètre 'firstName' ne doit pas être NULL");
        }

        if (userDB.getLastName() == null) {
            throw new InvalidParameterException("Le paramètre 'lastName' ne doit pas être NULL");
        }

        if (userDB.getMail() == null) {
            throw new InvalidParameterException("Le paramètre 'mail' ne doit pas être NULL");
        }
    }

    /*
    @Transactional
    public void createAndSaveUserRelationshipsInTransaction(UserDB user, List<GroupDB> groups) {
        user.setGroups(groups);
        this.userRepository.save(user);

        System.out.println("Relationship links were saved between a singer, bands, and instruments in a single transaction: " + user);
    }
    */
}
