%%part 1 arithmetic
%test case: add(inc(inc(zero)), inc(inc(inc(zero))), Z). (2 + 3).
%Z = inc(inc(inc(inc(inc(zero))))). (= 5).
add(zero, X, X).

%(x+1) + y = (z+1) if (x + y) = z.
add(inc(X), Y, inc(Z)) :- add(X,Y,Z).

mul(zero, X, zero).

%(x+1) * y = x*y + y = z
mul(inc(X), Y, Z) :- mul(X,Y,P), add(P, Y, Z).

exp(zero, X, 1).

exp(X, inc(Y), Z) :- mul(X,Y,P), mul(P,X,Z).

less(X, Y, Z).

%Org Charts etc
%X supervises Y or a supervisor of Y
supervises(X, Y) :-.

%X is a friend of Y
friend(X, Y) :-.

%x == Y
equals(X, Y) :-.

%distance from X to Y is Z hops
distance(X,Y,Z) :-.

%%Evaluating expressions
prod(C1,C2).

sum(C1, C2).

%evaluate x, return it in y.
%need to write an eval for each possible length of things
%need recursion to solve

eval(X, Y) :- .
eval(num(X), ?).
eval(sum(C1, C2), X) :-?

%%trees
height(leaf, 0).
height(parent(Child1, Child2), H) :-
	height(Child1, H1), height(Child2, H2), H is max(H1,H2) + 1.
%height(parent(leaf, parent(parent(leaf,leaf), leaf)), W). W = 3
%height(leaf,Z). Z = 0
%height(parent(leaf,leaf),W). W = 1.


