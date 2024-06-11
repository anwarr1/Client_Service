package org.example.client_service.models;

public class FinancialReport {
    private double totalIncoming;
    private double totalOutgoing;

    public FinancialReport(double totalIncoming, double totalOutgoing) {
        this.totalIncoming = totalIncoming;
        this.totalOutgoing = totalOutgoing;
    }

    public double getTotalIncoming() {
        return totalIncoming;
    }

    public double getTotalOutgoing() {
        return totalOutgoing;
    }
}
