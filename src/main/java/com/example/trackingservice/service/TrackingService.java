package com.example.trackingservice.service;

import com.example.trackingservice.message.Status;
import com.example.trackingservice.message.DispatchPreparing;
import com.example.trackingservice.message.TrackingStatusUpdated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrackingService {

    private static final String TRACKING_STATUS_TOPIC = "tracking.status";
    private static final Logger log = LoggerFactory.getLogger(TrackingService.class);
    private final KafkaTemplate<String, Object> kafkaProducer;

    public void process(DispatchPreparing p ayload) throws Exception {
        TrackingStatusUpdated trackingStatusUpdated =  new TrackingStatusUpdated(payload.getOrderId(), Status.PREPARING);
        log.info("Order Id coming from Order app: " + payload.getOrderId().toString());
        kafkaProducer.send(TRACKING_STATUS_TOPIC, trackingStatusUpdated).get();
    }
}
