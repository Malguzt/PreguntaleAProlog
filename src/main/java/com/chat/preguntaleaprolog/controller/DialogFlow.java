package com.chat.preguntaleaprolog.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.chat.preguntaleaprolog.controller.request.DialogFlowRequest;
import com.chat.preguntaleaprolog.controller.request.Parameters;
import com.chat.preguntaleaprolog.controller.response.DialogFlowResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.chat.preguntaleaprolog.core.interfaces.Prolog;
import org.apache.naming.factory.BeanFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dialogflow")
public class DialogFlow {

  private List<Prolog> prologList;

  public DialogFlow(List<Prolog> prologList) {
    this.prologList = prologList;
  }

  @PostMapping(produces = APPLICATION_JSON_VALUE)
  public ResponseEntity dialogFlow(@RequestBody DialogFlowRequest request) throws IOException, NoSuchMethodException {
    DialogFlowResponse dialogFlowResponse = new DialogFlowResponse();
    dialogFlowResponse
            .getFulfillmentMessages()
            .get(0)
            .getText()
            .setText(Arrays.asList(getResponse(request.getQueryResult().getAction(), request.getQueryResult().getParameters())));
    return ResponseEntity.status(HttpStatus.OK).body(dialogFlowResponse);
  }


  private String getResponse(String action, Parameters parameters) {
    Prolog userCase = prologList
            .stream()
            .filter(prolog ->
                    prolog.answers(action)
            )
            .findFirst()
            .get();

    try {
      return (String) userCase
              .getClass()
              .getMethod(action, Parameters.class).invoke(userCase,parameters);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }

    return "";
  }
}
