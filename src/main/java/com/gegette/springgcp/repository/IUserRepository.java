package com.gegette.springgcp.repository;

import com.gegette.springgcp.model.UserDB;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

public interface IUserRepository extends DatastoreRepository<UserDB, String> {

    UserDB findByFirstName(String search);

    UserDB findByLastName(String search);

    UserDB findByMail(String search);
}
