package com.chat.preguntaleaprolog.controller.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import lombok.Getter;

@JsonSerialize
public class DialogFlowResponse {
  @Getter
  private List<FulfillmentMessage> fulfillmentMessages = Arrays.asList(new FulfillmentMessage());
}
