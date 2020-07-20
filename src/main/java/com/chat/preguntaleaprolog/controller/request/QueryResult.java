package com.chat.preguntaleaprolog.controller.request;

import lombok.Getter;

public class QueryResult {
  @Getter
  private Intent intent;

  @Getter
  private String action;

  @Getter
  private Parameters parameters;
}
