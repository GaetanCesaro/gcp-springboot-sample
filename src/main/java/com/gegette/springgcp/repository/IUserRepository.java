package com.gegette.springgcp.repository;

import com.gegette.springgcp.model.UserDB;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import java.util.List;

public interface IUserRepository extends DatastoreRepository<UserDB, String> {

    List<UserDB> findByFirstNameContaining(String search);

    List<UserDB> findByLastNameContaining(String search);

    List<UserDB> findByMailContaining(String search);
}
