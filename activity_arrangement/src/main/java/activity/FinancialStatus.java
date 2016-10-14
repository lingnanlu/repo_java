package activity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 记录一次活动的收支情况
 */
public class FinancialStatus {

    Date date;
    TimeInterval timeInterval;
    int income;
    int outcome;
    int profit;

    public FinancialStatus(Date date, TimeInterval timeInterval, int income, int outcome) {
        this.date = date;
        this.timeInterval = timeInterval;
        this.income = income;
        this.outcome = outcome;
        this.profit = income - outcome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        sb.append(formater.format(date) + " ");
        sb.append(timeInterval + " ");
        sb.append("+" + income + " ");
        sb.append("-" + outcome + " ");
        sb.append(profit > 0 ? ("+" + profit) : profit);
        return sb.toString();
    }

    public int getIncome() {
        return income;
    }
    public int getOutcome() {
        return outcome;
    }
}
