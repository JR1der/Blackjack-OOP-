package main;

import blackjack_req.Table;

/**
 *
 * House class
 *
 * House class which has list of tables
 * If you want to change minimal or maximal bet on each table or add tables change data here
 */

public class House {
    public Table tables[] = new Table[3]; // creates an array of tables (you can change number of elements and add new tables)

    public House() {
        this.tables[0] = new Table(1, 25);
        this.tables[1] = new Table(25, 100);
        this.tables[2] = new Table(100, 1000);
    }
}
