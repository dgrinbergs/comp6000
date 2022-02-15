package com.comp6000.backend.grpc;

import com.comp6000.backend.genetic.Building;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class EventService {

  final Sinks.Many<Building> sink;
  private final Queue<Building> queue = new ArrayBlockingQueue<>(1);

  private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);

  public EventService() {
    this.sink = Sinks.many().unicast().onBackpressureBuffer(queue);

    sink.asFlux().subscribe(b -> System.out.println(b.id()));
  }

  public void consumeBuilding(Building building) {
    sink.emitNext(building, (signalType, emitResult) -> {
      LOGGER.info(signalType.toString());
      return true;
    });
    LOGGER.info("queue has {} items.", queue.size());
  }

}
