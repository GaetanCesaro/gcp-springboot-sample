package com.gegette.springgcp.service;

import com.gegette.springgcp.model.UserDB;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

public interface IUserRepository extends DatastoreRepository<UserDB, String> {
}
