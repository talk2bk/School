%%Evaluating expressions
eval(num(X), Z) :- Z is X.
eval(sum(X,Y), Z) :-  eval(X, X1), eval(Y, Y1), Z is X1 + Y1.
eval(prod(X,Y), Z) :- eval(X, X1), eval(Y, Y1), Z is X1 * Y1.
