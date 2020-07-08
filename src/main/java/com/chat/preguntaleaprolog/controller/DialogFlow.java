package com.chat.preguntaleaprolog.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.chat.preguntaleaprolog.controller.request.DialogFlowRequest;
import com.chat.preguntaleaprolog.controller.response.DialogFlowResponse;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dialogflow")
public class DialogFlow {

  @PostMapping(produces = APPLICATION_JSON_VALUE)
  public ResponseEntity dialogFlow(@RequestBody DialogFlowRequest request) {
    DialogFlowResponse dialogFlowResponse = new DialogFlowResponse();
    dialogFlowResponse.getFulfillmentMessages().get(0).getText().setText(new ArrayList<>());
    return ResponseEntity.status(HttpStatus.OK).body(dialogFlowResponse);
  }

}
