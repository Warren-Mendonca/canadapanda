package com.war.canadapanda.agents.payload.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AgentSignUp {
    String email;
    String firstName;
    String lastName;
    List<String> services;
    String primaryServiceKey;
    Integer phoneNum;
}
