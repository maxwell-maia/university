takes(tom, ct331).
takes(mary, ct331).
takes(joe, ct331).
takes(tom, ct345).
takes(mary, ct345).
instructs(bob, ct331).
instructs(ann, ct345).

teaches(Instructor, Student) :-
    instructs(Instructor, Course),
    takes(Student, Course).

classmates(Student1, Student2) :-
    takes(Student1, Course),
    takes(Student2, Course).	

contains1(Element, [Element | _]).

contains2(List, [_ | List]).


isNotElementInList(_, []).

isNotElementInList(Element, [Head | Tail]) :-
    Element \= Head,
    isNotElementInList(Element, Tail).