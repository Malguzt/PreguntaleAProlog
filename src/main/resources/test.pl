child_of(juan, pedro).
child_of(juan, maria).
child_of(esteban, pedro).
child_of(esteban, maria).
child_of(maria, fernando).
child_of(maria, julieta).
child_of(pedro, martin).
child_of(pedro, elodia).
child_of(martin, euclides).
descendent_of(X, Y) :-
    child_of(X, Y).
descendent_of(X, Y) :-
    child_of(Z, Y),
    descendent_of(X, Z).