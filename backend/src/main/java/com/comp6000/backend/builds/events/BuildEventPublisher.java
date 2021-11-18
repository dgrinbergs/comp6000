package com.comp6000.backend.builds.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

@Component
public class BuildEventPublisher implements ApplicationListener<BuildEvent>, Consumer<FluxSink<BuildEvent>> {

  private final Executor executor;
  private final BlockingQueue<BuildEvent> buildEvents;

  @Autowired
  public BuildEventPublisher(Executor executor) {
    this.executor = executor;
    this.buildEvents = new ArrayBlockingQueue<>(5, true);
  }

  @Override
  public void accept(FluxSink<BuildEvent> buildEventFluxSink) {
    this.executor.execute(() -> {
      while (true) {
        try {
          buildEventFluxSink.next(buildEvents.take());
        } catch (InterruptedException e) {
          ReflectionUtils.rethrowRuntimeException(e);
        }
      }
    });
  }

  @Override
  public void onApplicationEvent(BuildEvent event) {
    this.buildEvents.offer(event);
  }
}
