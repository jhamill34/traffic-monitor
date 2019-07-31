package com.traffic.services;

import java.util.List;

import com.traffic.models.TrafficSnapshot;
import com.traffic.repositories.TrafficSnapshotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrafficSnapshotService {
    @Autowired 
    private TrafficSnapshotRepository trafficSnapshotRepository;

    public void store(TrafficSnapshot snapshot) {
        trafficSnapshotRepository.save(snapshot);
    }

    public void storeAll(List<TrafficSnapshot> snapshots) {
        trafficSnapshotRepository.saveAll(snapshots);
    }
}