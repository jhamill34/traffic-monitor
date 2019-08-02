package com.traffic.schedules;

import com.traffic.services.GoogleMapsService;
import com.traffic.services.TrafficSnapshotService;
import com.traffic.models.TrafficSnapshot;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CommuteSchedule {
    private Logger logger = LoggerFactory.getLogger(CommuteSchedule.class);

    @Value("${locations.home}")
    private String home;

    @Value("${locations.work}")
    private String work;

    @Autowired
    private TrafficSnapshotService trafficSnapshotService;

    @Autowired
    private GoogleMapsService googleMapsService;

    // Will Run every Monday through Friday afrom 5am to 7am every 5 minutes
    @Scheduled(cron = "0 */5 5-7 * * MON-FRI", zone="GMT-7")
    public void morningCommuteTask() {
        logger.info("Fetching morning commute time...");
        List<TrafficSnapshot> routes = googleMapsService.fetchSnapshot(home, work);
        trafficSnapshotService.storeAll(routes);
    }

    // Will Run every Monday through Friday afrom 3pm to 7pm every 5 minutes
    @Scheduled(cron = "0 */5 15-19 * * MON-FRI", zone="GMT-7")
    public void afternoonCommuteTask() {
        logger.info("Fetching afternoon commute time...");
        List<TrafficSnapshot> routes = googleMapsService.fetchSnapshot(work, home);
        trafficSnapshotService.storeAll(routes);
    }
}