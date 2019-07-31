package com.traffic.repositories;

import com.traffic.models.TrafficSnapshot;

import org.springframework.data.repository.CrudRepository;

public interface TrafficSnapshotRepository extends CrudRepository<TrafficSnapshot, Long> {}