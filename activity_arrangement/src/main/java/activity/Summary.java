package activity;

import java.util.ArrayList;
import java.util.List;

public class Summary {
    
    List<FinancialStatus> financialStatuses = new ArrayList<>();
    
    public void addFinacialStatus(FinancialStatus record) {
        financialStatuses.add(record);
    }

    private int totalIncome() {
        int totalIncome = 0;
        for (FinancialStatus record : financialStatuses) {
            totalIncome += record.getIncome();
        }
        return totalIncome;
    }

    private int totalPayment() {
        int totalPayment = 0;
        for (FinancialStatus record : financialStatuses) {
            totalPayment += record.getOutcome();
        }
        return totalPayment;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("[Summary]\n");
        sb.append("\n");

        for (FinancialStatus record : financialStatuses) {
            sb.append(record + "\n");
        }

        sb.append("\n");
        int totalIncome = totalIncome();
        int totalPayment = totalPayment();
        sb.append("Total Income: " + totalIncome + "\n");
        sb.append("Total Payment: " + totalPayment + "\n");
        int profit = totalIncome - totalPayment;
        sb.append("Profit: " + profit + "\n");

        return sb.toString();
    }


}
