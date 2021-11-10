package com.comp6000.backend.generation;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Component
public class GenerationEventPublisher implements ApplicationListener<GenerationEvent>, Consumer<FluxSink<GenerationEvent>> {

  private final Executor executor;
  private final BlockingQueue<GenerationEvent> generationEvents;

  public GenerationEventPublisher() {
    this.executor = Executors.newSingleThreadExecutor();
    this.generationEvents = new ArrayBlockingQueue<>(5, true);
  }

  @Override
  public void accept(FluxSink<GenerationEvent> generationEventFluxSink) {
    this.executor.execute(() -> {
      while (true) {
        try {
          generationEventFluxSink.next(generationEvents.take());
        } catch (InterruptedException e) {
          ReflectionUtils.rethrowRuntimeException(e);
        }
      }
    });
  }

  @Override
  public void onApplicationEvent(GenerationEvent event) {
    this.generationEvents.offer(event);
  }
}
