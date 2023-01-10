getAllLivings(I):-
    findall(X, (mapa1(_,_,X),X>0),L1),
    length(L1, R1),
    findall(Y,(mapa2(_,_,Y),Y=2),L2),
    length(L2, R2),
    I is R1 + R2.

getAllDeath(I) :- findall(X,(mapa2(_,_,X),X=7),L1),
    length(L1, I).

getAllByRefer(R,I) :- findall(X,(mapa1(_,_,X),X=R),L1),length(L1, I).
getAllPlants(I) :- findall(X,(mapa2(_,_,X),X=2),L1),length(L1, I).