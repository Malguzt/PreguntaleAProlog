package com.chat.preguntaleaprolog.core;

import com.chat.preguntaleaprolog.controller.request.Parameters;
import com.chat.preguntaleaprolog.core.interfaces.Prolog;
import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

@Component
public class Family implements Prolog {

    @Value("classpath:test.pl")
    Resource prologFile;

    @Override
    public boolean answers(String consult) {
        return Arrays.asList("descendetnOf", "sonOf").contains(consult);
    }

    @Override
    public String descendetnOf(Parameters parameters) throws IOException {
        Query q1 =
                new Query(
                        "consult",
                        new Term[] {new Atom(prologFile.getFile().getAbsolutePath())}
                );

        q1.hasSolution();
        Query q2 =
                new Query(
                        "descendent_of",
                        new Term[] {new Atom(parameters.getDescendent().getName().toLowerCase()), new Atom(parameters.getAscendent().getName().toLowerCase())}
                );
        String response = (q2.hasSolution() ? " " : " no") + " es descendiente de ";
        return  parameters.getDescendent().getName() + response + parameters.getAscendent().getName();
    }


    @Override
    public String sonOf(Parameters parameters) throws IOException {
        Query q1 =
                new Query(
                        "consult",
                        new Term[] {new Atom(prologFile.getFile().getAbsolutePath())}
                );

        q1.hasSolution();
        Query q2 =
                new Query(
                        "child_of",
                        new Term[] {new Atom(parameters.getDescendent().getName().toLowerCase()), new Atom(parameters.getAscendent().getName().toLowerCase())}
                );
        String response = (q2.hasSolution() ? " " : " no") + " es hijo de ";
        return  parameters.getDescendent().getName() + response + parameters.getAscendent().getName();
    }
}
