package com.war.canadapanda.survey.conroller;

import com.war.canadapanda.core.payload.response.MessageResponse;
import com.war.canadapanda.survey.payload.request.SurveyAddRequest;
import com.war.canadapanda.survey.payload.response.SurveyNode;
import com.war.canadapanda.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("survey")
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    @GetMapping("/{surveyid}")
    public SurveyNode getSurveyNode(@PathVariable(value = "surveyid") String surveyid) {
        return surveyService.getSurveyNode(surveyid);
    }

    //TODO: 10/24/2019 : Add Admin role and make accessible via admin
    @PostMapping("/addNode")
    public MessageResponse addSurveyNode(@Valid @RequestBody SurveyAddRequest surveyAddRequest) {
        return surveyService.addSurveyNode(surveyAddRequest);
    }

    //TODO: 10/24/2019 : Add Admin role and make accessible via admin
    @GetMapping("/fullSurvey")
    public void printFullSurvey() {
        surveyService.printFullSurvey();
    }


}
