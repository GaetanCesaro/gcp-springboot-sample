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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service(value = "UserService")
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserDB> getUsers(String search) {
        if (search == null || "".equals(search)) {
            throw new InvalidParameterException("Le paramètre 'search' ne doit pas être NULL");
        }

        List<UserDB> usersDB = userRepository.findByMailContaining(search);
        Stream.concat(usersDB.stream(), userRepository.findByFirstNameContaining(search).stream()).distinct().collect(Collectors.toList());
        Stream.concat(usersDB.stream(), userRepository.findByLastNameContaining(search).stream()).distinct().collect(Collectors.toList());

        if(usersDB != null && usersDB.isEmpty()) {
            throw new UserNotFoundException(String.format("Aucun utilisateur n'a été trouvé pour la recherche %s", search));
        }

        return usersDB;
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
