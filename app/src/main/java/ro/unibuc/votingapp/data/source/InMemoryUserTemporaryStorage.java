package ro.unibuc.votingapp.data.source;

import java.util.LinkedList;

import ro.unibuc.votingapp.data.Utilizator;


final class InMemoryUserTemporaryStorage {
    private static final LinkedList < Utilizator > q = new LinkedList <>();
    private static int nrOfUsers = 0;

    void addUserInMemory( Utilizator utilizator ) {
        q.add( utilizator );
        nrOfUsers++;
    }

    Utilizator removeUserInMemory() {
        Utilizator utilizator = q.remove();
        nrOfUsers--;          //daca mu s-a aruncat exceptie, inseamna ca putem scadea nr de elemente
        return utilizator;
    }

    int getNrOfUsers() {
        return nrOfUsers;
    }
}
