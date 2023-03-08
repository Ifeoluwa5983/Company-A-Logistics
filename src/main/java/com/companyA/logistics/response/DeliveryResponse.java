package com.companyA.logistics.response;

import java.util.List;

public class DeliveryResponse {

    private List<String> optimalRoute;
    private double deliveryCost;

    public List<String> getOptimalRoute() {
        return optimalRoute;
    }

    public void setOptimalRoute(List<String> optimalRoute) {
        this.optimalRoute = optimalRoute;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }
}
