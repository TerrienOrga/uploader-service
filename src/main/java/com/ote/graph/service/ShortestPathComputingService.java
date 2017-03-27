package com.ote.graph.service;

import com.ote.graph.model.ShortestPathResult;
import com.ote.graph.service.persistence.GraphRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class ShortestPathComputingService implements IShortestPathComputingService {

    @Autowired
    private GraphRepository graphRepository;

    public ShortestPathResult findPath(String source, String destination) {

        List<String> nodes = graphRepository.findAllNodes();

        Map<String, Double> distance = new HashMap<>();
        nodes.stream().forEach(p -> distance.put(p, Double.MAX_VALUE));
        distance.put(source, 0d);
        Map<String, String> predecessors = new HashMap<>();
        Set<String> unVisitedNodes = new HashSet<>(nodes);

        while (!unVisitedNodes.isEmpty()) {
            String nearestNeighbour = findMin(unVisitedNodes, distance);
            log.info(String.format("Nearest : %s", nearestNeighbour));
            if (nearestNeighbour != null) {
                unVisitedNodes.remove(nearestNeighbour);
                List<String> neighbours = graphRepository.findNeighbours(nearestNeighbour);
                neighbours.forEach(p -> updateDistance(nearestNeighbour, p, distance, predecessors));
            } else {
                break;
            }
        }

        if (!predecessors.containsKey(destination))
            return null;

        ShortestPathResult result = new ShortestPathResult();
        result.setSource(source);
        result.setDistance(distance.get(destination));

        String node = destination;
        while (!node.equals(source)) {
            result.getWay().add(node);
            node = predecessors.get(node);
        }
        Collections.reverse(result.getWay());
        return result;
    }

    private String findMin(Set<String> nodes, Map<String, Double> distance) {

        AtomicReference<Double> min = new AtomicReference<>(Double.MAX_VALUE);
        AtomicReference<String> node = new AtomicReference<>();

        nodes.forEach(p -> {
            Double dist = distance.get(p);
            if (dist < min.get()) {
                min.set(dist);
                node.set(p);
            }
        });

        return node.get();
    }

    private void updateDistance(String source, String destination, Map<String, Double> distance, Map<String, String> predecessors) {

        Double distanceUntilDestination = distance.get(destination);
        Double distanceUntilSource = distance.get(source);
        Double distanceUntilDestinationViaSource = distanceUntilSource + graphRepository.findDistance(source, destination);

        if (distanceUntilDestination > distanceUntilDestinationViaSource) {
            distance.put(destination, distanceUntilDestinationViaSource);
            predecessors.put(destination, source);
        }
    }
}
