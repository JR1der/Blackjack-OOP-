package blackjack_req;

/**
 *
 * Class table
 *
 * Creates a table with parameters such as maximal bet and minimal bet
 */


public class Table {
    public int mxBid;

    public int mnBid;

    public Table(int mnBid, int mxBid) { // constructor with parameters (minimal value and maximal values of the table
        this.mnBid = mnBid;
        this.mxBid = mxBid;
    }

}
