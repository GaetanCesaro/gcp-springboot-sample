package com.gegette.springgcp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Field;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Reference;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class UserDB {

    @Id
    @Field(name = "mail")
    private String mail;

    @Field(name = "last_name")
    private String lastName;

    @Field(name = "first_name")
    private String firstName;

    @Reference
    private List<GroupDB> groups;

}
