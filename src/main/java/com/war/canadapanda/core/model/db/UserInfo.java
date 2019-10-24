package com.war.canadapanda.core.model.db;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@DynamoDBTable(tableName = "UserInfo")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@NoArgsConstructor
public class UserInfo {

    String id;
    String username;
    String password;
    String email;
    String firstName;
    String lastName;
    List<String> roles;

    public UserInfo(String username, String email, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }
    @DynamoDBAttribute
    public String getUsername() {
        return username;
    }
    @DynamoDBAttribute
    public String getPassword() {
        return password;
    }
    @DynamoDBAttribute
    public String getEmail() {
        return email;
    }
    @DynamoDBAttribute
    public String getFirstName() {
        return firstName;
    }
    @DynamoDBAttribute
    public String getLastName() {
        return lastName;
    }
    @DynamoDBAttribute
    public List<String> getRoles() {
        return roles;
    }
}
