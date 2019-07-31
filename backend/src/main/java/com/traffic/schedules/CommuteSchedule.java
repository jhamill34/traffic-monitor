package com.traffic.schedules;

import com.traffic.services.GoogleMapsService;
import com.traffic.services.TrafficSnapshotService;
import com.traffic.models.TrafficSnapshot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CommuteSchedule {
    @Value("${locations.home}")
    private String home;

    @Value("${locations.work}")
    private String work;

    @Autowired
    private TrafficSnapshotService trafficSnapshotService;

    @Autowired
    private GoogleMapsService googleMapsService;

    // Will Run every Monday through Friday afrom 5am to 7am every 5 minutes
    // @Scheduled(cron = "*/5 5-7 * * 1-5 *")
    public void morningCommuteTask() {
        List<TrafficSnapshot> routes = googleMapsService.fetchSnapshot(home, work);
        trafficSnapshotService.storeAll(routes);
    }

    // Will Run every Monday through Friday afrom 3pm to 7pm every 5 minutes
    // @Scheduled(cron = "*/5 15-19 * * 1-5 *")
    @Scheduled(cron = "0 * * * * *")
    public void afternoonCommuteTask() {
        List<TrafficSnapshot> routes = googleMapsService.fetchSnapshot(work, home);
        trafficSnapshotService.storeAll(routes);
    }
}