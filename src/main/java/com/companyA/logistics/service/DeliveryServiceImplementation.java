package com.companyA.logistics.service;

import com.companyA.logistics.dto.CreateDelivery;
import com.companyA.logistics.exception.CompanyAException;
import com.companyA.logistics.models.Delivery;
import com.companyA.logistics.models.DeliveryLocation;
import com.companyA.logistics.repository.DeliveryLocationRepository;
import com.companyA.logistics.repository.DeliveryRepository;
import com.companyA.logistics.response.DeliveryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class DeliveryServiceImplementation implements DeliveryService{

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    DeliveryLocationRepository deliveryLocationRepository;

    @Override
    public DeliveryResponse createDelivery(CreateDelivery createDelivery) throws CompanyAException {
        try {
            Delivery delivery = new Delivery();
            if(deliveryLocationRepository.count() < 3){
                throw new CompanyAException("(Minimum of 3 locations must exist before deliveries\n" +
                        "can start");
            }
            DeliveryLocation originDeliveryLocation = deliveryLocationRepository.findById(createDelivery.getPickup()).get();
            DeliveryLocation destinationDeliveryLocation = deliveryLocationRepository.findById(createDelivery.getDestination()).get();
            if(originDeliveryLocation == null || destinationDeliveryLocation == null){
                throw new CompanyAException("Location does not exist");
            }
            String origin = originDeliveryLocation.getLocation() + ", " + originDeliveryLocation.getState() + ", " + originDeliveryLocation.getCountry();
            String destination = destinationDeliveryLocation.getLocation() + ", " + destinationDeliveryLocation.getState() + ", " + destinationDeliveryLocation.getCountry();

            DeliveryResponse deliveryResponse = generateDeliveryRoute(origin, destination);

            List<String> items = new ArrayList<>();
            items.add(createDelivery.getItem1());
            items.add(createDelivery.getItem2());
            items.add(createDelivery.getItem3());
            items.add(createDelivery.getItem4());
            items.add(createDelivery.getItem5());

            delivery.setDeliveryCost(deliveryResponse.getDeliveryCost());
            delivery.setItems(items);
            delivery.setDestination(destinationDeliveryLocation);
            delivery.setPickup(originDeliveryLocation);
            deliveryRepository.save(delivery);

            return deliveryResponse;

        }catch (Exception exception){
            throw new CompanyAException("An error occurred");
        }
    }

    private DeliveryResponse generateDeliveryRoute(String origin, String destination) {

        // Create a graph to represent the delivery locations
        Map<String, Map<String, Double>> graph = new HashMap<>();

        // Add edges to the graph representing the distances between the delivery locations

        // Use Dijkstra's algorithm to find the shortest path between the origin and destination locations
        List<String> path = dijkstra(graph, origin, destination);

        // Compute the delivery cost based on the distance between the origin and destination locations
        double distance = computeDistance(path, graph);
        double deliveryCost = distance * 1;

        // Create a DeliveryResponse object to return to the user
        DeliveryResponse response = new DeliveryResponse();
        response.setOptimalRoute(path);
        response.setDeliveryCost(deliveryCost);

        return response;
    }

    private double computeDistance(List<String> path, Map<String, Map<String, Double>> graph) {
        double distance = 0.0;
        for (int i = 0; i < path.size() - 1; i++) {
            String from = path.get(i);
            String to = path.get(i + 1);
            distance += graph.get(from).get(to);
        }
        return distance;
    }

    public List<String> dijkstra(Map<String, Map<String, Double>> graph, String start, String end) {
        // Initialize distances and visited nodes
        Map<String, Double> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        Set<String> visited = new HashSet<>();

        // Set all distances to infinity and the start node to 0
        for (String node : graph.keySet()) {
            distances.put(node, Double.POSITIVE_INFINITY);
        }
        distances.put(start, 0.0);

        // Visit nodes in order of shortest distance from the start
        while (!visited.contains(end)) {
            // Find the node with the shortest distance that hasn't been visited
            String current = null;
            double shortestDistance = Double.POSITIVE_INFINITY;
            for (String node : graph.keySet()) {
                if (!visited.contains(node) && distances.get(node) < shortestDistance) {
                    current = node;
                    shortestDistance = distances.get(node);
                }
            }
            if (current == null) {
                break;  // No path found
            }

            // Update the distances of neighboring nodes
            for (Map.Entry<String, Double> neighbor : graph.get(current).entrySet()) {
                String node = neighbor.getKey();
                double distance = neighbor.getValue();
                double newDistance = distances.get(current) + distance;
                if (newDistance < distances.get(node)) {
                    distances.put(node, newDistance);
                    previous.put(node, current);
                }
            }

            visited.add(current);
        }

        // Build the path from the end node to the start node
        List<String> path = new ArrayList<>();
        String current = end;
        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }
        Collections.reverse(path);

        return path;
    }

}
