package com.war.canadapanda.survey.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@DynamoDBTable(tableName = "Survey")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@NoArgsConstructor
public class SurveyModel {

    String surveyid;
    String question;
    //collection of id + description of child
    Map<String, String> children;
    String parent;

    public SurveyModel(String surveyid, String question, String parent) {
        this.surveyid = surveyid;
        this.question = question;
        this.parent = parent;
    }

    @DynamoDBHashKey
    public String getSurveyid() {
        return surveyid;
    }

    @DynamoDBAttribute
    public String getQuestion() {
        return question;
    }

    @DynamoDBAttribute
    public Map<String, String> getChildren() {
        return children;
    }

    @DynamoDBAttribute
    public String getParent() {
        return parent;
    }
}
