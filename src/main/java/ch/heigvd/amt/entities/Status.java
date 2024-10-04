package ch.heigvd.amt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.Instant;

@Entity
public class Status {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Probe probe;

    private Instant timestamp;

    private Integer status;

    private Integer duration;

    public void setId(long id) {
        this.id = id;
    }

    public void setProbe(Probe probe) {
        this.probe = probe;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public Probe getProbe() {
        return probe;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getDuration() {
        return duration;
    }
}
