instructor(cs152, turing).
instructor(cs151, edison).

enrolled(cs152, godel).
enrolled(cs152, escher).
enrolled(cs152, bach).
enrolled(cs152, vonNeumann).

teaches(X, Y) :- instructor(C, X), enrolled(C, Y).

