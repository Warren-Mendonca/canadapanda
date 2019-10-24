package com.war.canadapanda.security.payload.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class SignUpPayload {

    @NotBlank
    @Size(min = 3, max = 40)
    String username;
    @NotBlank
    @Size(min = 6, max = 20)
    String password;
    @Email
    @Size(max = 40)
    String email;
    @NotBlank
    @Size(min = 3, max = 20)
    String firstName;
    String lastName;

}