%parent(the parent, the child)
parent(homer, bart).
parent(homer, lisa).
parent(homer, maggie).

parent(abe, homer).
parent(mona, homer).

parent(marge, bart).
parent(marge, lisa).
parent(marge, maggie).

parent(clancy, marge).
parent(jacquelin, marge).

parent(clancy, selma).
parent(clancy, patty).
parent(jacquelin, selma).
parent(jacquelin, patty).

%child(the child, the parent)
child(Y, X) :- parent(X,Y).

%ancestor(the ancestor, the child)
%ancestor if ancestors child is ancestor of child, continue to parent
ancestor(X , Y) :- parent(X, Y).
ancestor(X, Y) :- parent(Z, Y), ancestor(X, Z).

% sibling(a child, another child) :- parent of one child should be
% parent of another
sibling(X, Y) :- parent(P, X), parent(P, Y).
