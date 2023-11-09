package main;

import blackjack_req.Table;

public class House {
    public Table tables[] = new Table[3];

    public House() {
        this.tables[0] = new Table(1, 25);
        this.tables[1] = new Table(25, 100);
        this.tables[2] = new Table(100, 1000);
    }
}
