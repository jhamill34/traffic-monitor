package com.traffic.services;

import com.traffic.models.TrafficSnapshot;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapsService {
    @Value("${google.maps.key}")
    private String apiKey;

    public List<TrafficSnapshot> fetchSnapshot(String from, String to) {
        List<TrafficSnapshot> results = new ArrayList<>();

        GeoApiContext context = new GeoApiContext.Builder()
            .apiKey(apiKey)
            .build();

        Date d = new Date();

        try {
          DirectionsResult req = DirectionsApi 
            .getDirections(context, from, to)
            .alternatives(true)
            .departureTimeNow()
            .mode(TravelMode.DRIVING)
            .await();
    
          for (DirectionsRoute route : req.routes) {
            // Create snapshot
            TrafficSnapshot s = new TrafficSnapshot(); 
            s.setTime(d.getTime() / 1000);
            s.setSummary(route.summary);
            s.setEncodedPolyline(route.overviewPolyline.getEncodedPath());
    
            for (DirectionsLeg leg : route.legs) {
              s.setDuration(leg.duration.inSeconds);
              s.setDurationInTraffic(leg.durationInTraffic.inSeconds);
              s.setDistance(leg.distance.inMeters);
            }
    
            // add to results
            results.add(s);
          }
        } catch(Exception e) {

        }

        context.shutdown();

        return results;
    }
}