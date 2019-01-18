package com.gegette.springgcp.controller;

import com.gegette.springgcp.exception.BasicTypeResponseWrapper;
import com.gegette.springgcp.exception.UserNotFoundException;
import com.gegette.springgcp.model.UserDB;
import com.gegette.springgcp.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@BasePathAwareController
@RestController
@Api(tags = "Utilisateurs")
public class UserController {

    @Autowired
    private UserService userRepositoryService;

    @GetMapping(value = "/user/{search}")
    @ApiOperation("Recherche d'utilisateurs par un like sur leur nom, prénom ou mail")
    @ResponseBody
    public ResponseEntity<Collection<UserDB>> getUsers(@PathVariable("search") String search) {
        try {
            Collection<UserDB> usersDB = userRepositoryService.getUsers(search);
            return new ResponseEntity(usersDB, HttpStatus.OK);
        } catch (UserNotFoundException dpnfe) {
            return new ResponseEntity(new BasicTypeResponseWrapper<String>(dpnfe.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/user")
    @ApiOperation("Création/Mise à jour d'un utilisateur")
    @ResponseBody
    public ResponseEntity<Void> postUser(@RequestBody UserDB userDB) {
        userDB = userRepositoryService.postUser(userDB);
        return new ResponseEntity(userDB, HttpStatus.CREATED);
    }

}
