package com.war.canadapanda.survey.service;

import com.war.canadapanda.core.payload.response.MessageResponse;
import com.war.canadapanda.core.repositories.SurveyRepository;
import com.war.canadapanda.survey.model.SurveyModel;
import com.war.canadapanda.survey.payload.request.SurveyAddRequest;
import com.war.canadapanda.survey.payload.response.SurveyNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    private static String indent = "";

    public SurveyNode getSurveyNode(String surveyid) {
        SurveyModel surveyModel = surveyRepository.findById(surveyid)
                .orElseThrow(() -> new RuntimeException("No survey found with username : " + surveyid));
        return new SurveyNode(surveyModel.getSurveyid(), surveyModel.getQuestion(), surveyModel.getChildren(), surveyModel.getParent());
    }

    public MessageResponse addSurveyNode(SurveyAddRequest surveyNewChild) {

        // find parent
        SurveyModel surveyModelParent = surveyRepository.findById(surveyNewChild.getParentid())
                .orElseThrow(() -> new RuntimeException("No survey found with username : " + surveyNewChild.getParentid()));

        // create child key = parent key + child key
        String childKey = surveyModelParent.getSurveyid() + surveyNewChild.getIdkey();

        // TODO: 10/24/2019 : if question of parent is null add new question
        //  1. add null check and add to parent object

        // check if need to initialize parent map & add key and desc to parent
        Map<String, String> children = Optional.ofNullable(surveyModelParent.getChildren()).orElse(new HashMap<>());
        children.put(childKey, surveyNewChild.getDescription());
        surveyModelParent.setChildren(children);

        // TODO: 10/24/2019 : if question of parent is null add new question
        //  2. stop adding to child
        SurveyModel childSurveyModel = new SurveyModel(childKey, surveyNewChild.getQuestion(), surveyModelParent.getSurveyid());

        List<SurveyModel> addList = Arrays.asList(surveyModelParent, childSurveyModel);
        surveyRepository.saveAll(addList);

        return new MessageResponse(true, "Added new node with child key " + childKey);

    }

    public void printFullSurvey() {
        SurveyModel surveyModel = surveyRepository.findById("R")
                .orElseThrow(() -> new RuntimeException("No root found"));
        printAllChildren(surveyModel);

    }

    private void printAllChildren(SurveyModel node) {
        indent = indent + "-";
        if (node.getQuestion() != null) System.out.println(indent + "Q. " + node.getQuestion());

        if (node.getChildren() != null) {
            for (String child : node.getChildren().keySet()) {
                System.out.println(indent + "A. " + node.getChildren().get(child));
                SurveyModel surveyModel = surveyRepository.findById(child)
                        .orElseThrow(() -> new RuntimeException("No child found with key" + child));
                printAllChildren(surveyModel);

            }
        }
        indent = indent.substring(0, indent.length() - 1);
    }

}
