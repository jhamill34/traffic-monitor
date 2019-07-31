package com.traffic.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class TrafficSnapshot {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id; 
 
  @Column
  private long time;
  
  @Column
  private String summary;

  @Column
  private long duration;
  
  @Column
  private long durationInTraffic;
  
  @Column
  private long distance;

  @Lob
  @Column
  private String encodedPolyline;

  public TrafficSnapshot() {}

  public long getId() { return this.id; }
  public void setId(long id) { this.id = id; }

  public long getTime() { return this.time; }
  public void setTime(long time) { this.time = time; }

  public String getSummary() { return this.summary; }
  public void setSummary(String summary) { this.summary = summary; }

  public long getDuration() { return this.duration; }
  public void setDuration(long duration) { this.duration = duration; }

  public long getDurationInTraffic() { return this.durationInTraffic; }
  public void setDurationInTraffic(long duration) { this.durationInTraffic = duration; }

  public long getDistance() { return this.distance; }
  public void setDistance(long distance) { this.distance = distance; }

  public String getEncodedPolyline() { return this.encodedPolyline; }
  public void setEncodedPolyline(String encodedPolyline) { this.encodedPolyline = encodedPolyline; }
}