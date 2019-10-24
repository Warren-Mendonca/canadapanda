package com.war.canadapanda.security.payload.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class LoginPayload {
    @NotBlank
    String username;
    @NotBlank
    String password;
}
