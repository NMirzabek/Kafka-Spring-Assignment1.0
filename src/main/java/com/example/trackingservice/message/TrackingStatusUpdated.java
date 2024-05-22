package com.example.trackingservice.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class TrackingStatusUpdated {
    private UUID orderId;
    private Status status;

    public TrackingStatusUpdated(UUID orderId, Status status) {
        this.orderId = orderId;
        this.status = status;
    }
}
