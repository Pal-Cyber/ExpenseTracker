
import java.sql.Date;

public class Expense {
    private String title;
    private double amount;
    private String category;
    private Date date;

    public Expense(String title, double amount, String category, Date date) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Getters
    public String getTitle() { return title; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public Date getDate() { return date; }
}
