package com.chat.preguntaleaprolog.core.interfaces;

import com.chat.preguntaleaprolog.controller.request.Parameters;

import java.io.IOException;

public interface Prolog {
    boolean answers(String consult);

    String descendetnOf(Parameters parameters) throws IOException;

    String sonOf(Parameters parameters) throws IOException;
}
