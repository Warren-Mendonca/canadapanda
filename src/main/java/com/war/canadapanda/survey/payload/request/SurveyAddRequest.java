package com.war.canadapanda.survey.payload.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SurveyAddRequest {
    // append to parent id and add into parent map
    @NotBlank
    String idkey;
    // add into parent map
    @NotBlank
    String description;
    // question for parent node
    String question;
    @NotBlank
    String parentid;
}
