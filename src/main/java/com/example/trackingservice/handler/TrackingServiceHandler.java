package com.example.trackingservice.handler;

import com.example.trackingservice.message.DispatchPreparing;
import com.example.trackingservice.service.TrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TrackingServiceHandler {

    private static final Logger log = LoggerFactory.getLogger(TrackingServiceHandler.class);
    private final TrackingService trackingService;

    @KafkaListener(
            id = "trackingConsumerClient",
            topics = "dispatch.tracking",
            groupId = "dispatch.tracking.status.consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(DispatchPreparing payload){
        log.info("Received message in tracking service: " + payload);
        try {
            trackingService.process(payload);
        }catch (Exception e){
            log.error("Processing failure: " , e);
        }
    }
}
