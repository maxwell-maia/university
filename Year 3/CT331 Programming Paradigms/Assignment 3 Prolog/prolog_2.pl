membr(X, [X|T]).
membr(X, [H|T]):- membr(X, T).


isNotElementInList(_, []).
isNotElementInList(X,[H|T]) :- X \= H, isNotElementInList(X, T).


conc([ ], L, L).
conc([X|L1], L2, [X|L3]):-
conc(L1, L2, L3).


mergeLists([], List2, List3, Merged) :-
    mergeListsHelper(List2, List3, Merged).
mergeLists([H|T], List2, List3, [H|MergedTail]) :-
    mergeLists(T, List2, List3, MergedTail).

mergeListsHelper([], L, L).
mergeListsHelper([H|T], List2, [H|MergedTail]) :-
    mergeListsHelper(T, List2, MergedTail).


reverseList(L, R):-
reverse2(L, [], R).
reverse2([], R, R).
reverse2([H|T], Temp, R) :-
reverse2(T, [H|Temp], R).


insertInOrder(El, [], [El]).
insertInOrder(El, [H|T], [El, H|T]) :- El =< H.
insertInOrder(El, [H|T], [H|NewTail]) :-
    El > H,
    insertInOrder(El, T, NewTail).