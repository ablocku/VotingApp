package ro.unibuc.votingapp.data.source;

import java.util.LinkedList;

import ro.unibuc.votingapp.data.VotAnonim;


final class InMemoryTemporaryStorage {
    private static final LinkedList<VotAnonim> q = new LinkedList<>();
    private static int nrOfElements = 0;

    void addInMemory(VotAnonim votAnonim) {
        q.add(votAnonim);
        nrOfElements++;
    }

    VotAnonim removeInMemory() {
        VotAnonim votAnonim = q.remove();
        nrOfElements--;          //daca mu s-a aruncat exceptie, inseamna ca putem scadea nr de elemente
        return votAnonim;
    }

    int getNrOfElements() {
        return nrOfElements;
    }
}
