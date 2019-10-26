package com.war.canadapanda.agents.payload.reponse;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AgentInfo {

    String firstName;
    String lastName;
    String email;
    Integer number;
    List<String> services;

    public AgentInfo(String firstName, String lastName, String email, Integer number, List<String> services) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
        this.services = services;
    }

}
