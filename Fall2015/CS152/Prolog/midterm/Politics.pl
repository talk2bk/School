representative(ryan, precinct1).
representative(trump, precinct2).
representative(sanders, precinct3).
representative(brown, precinct4).
representative(smith, precinct5).

state(precinct1, iowa).
state(precinct2, iowa).
state(precinct3, iowa).
state(precinct4, utah).
state(precinct5, utah).

party(ryan, republican).
party(trump, republican).
party(sanders, democrat).
party(brown, republican).
party(smith, republican).


caucus(R1, R2) :- representative(R1, P1), representative(R2, P2), party(R1, P), party(R2, P), state(P1, S), state(P2, S), R1 \= R2.

caucuses(R1,R2) :-
	representative(R1, P1), representative(R2, P2),
	state(P1, S), state(P2, S2),
	party(R1, P3), party(R2, P4),
	R1 \= R2, S == S2, P3 == P4.
