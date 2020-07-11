package com.chat.preguntaleaprolog.controller;

import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/prolog")
public class Prolog {

    @Value("classpath:test.pl")
    Resource prologFile;

    @GetMapping("{descendent}/{ascendent}")
    public ResponseEntity prolog(
            @PathVariable("descendent") String descendent,
            @PathVariable("ascendent") String ascendent) throws IOException {
        Query q1 =
                new Query(
                        "consult",
                        new Term[] {new Atom(prologFile.getFile().getAbsolutePath())}
                );

        q1.hasSolution();
        Query q2 =
                new Query(
                        "descendent_of",
                        new Term[] {new Atom(descendent), new Atom(ascendent)}
                );
        return ResponseEntity.ok(q2.hasSolution());
    }
}
