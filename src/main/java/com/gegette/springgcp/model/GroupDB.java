package com.gegette.springgcp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Field;
import org.springframework.data.annotation.Id;

@Entity
@Data
@AllArgsConstructor
public class GroupDB {

    @Id
    @Field(name = "group_name")
    private String groupname;

}
