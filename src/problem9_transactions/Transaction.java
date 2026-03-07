package problem9_transactions;

public class Transaction {

    int id;
    int amount;
    String merchant;
    String account;
    long time; // timestamp in milliseconds

    public Transaction(int id, int amount, String merchant, String account, long time) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
        this.account = account;
        this.time = time;
    }
}
