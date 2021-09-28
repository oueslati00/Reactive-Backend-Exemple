package prg.sid.eventservice;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;


@RestController
public class EventReactiveRestApi {

    @GetMapping(value = "/streamEvent/{id}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Event> listEvents(@PathVariable String id ){
        Flux<Long> interval = Flux.interval(Duration.ofMillis(1000));
        Flux<Event> events = Flux.fromStream(Stream.generate(()->{
           Event event = new Event();
           Instant i = Instant.now();
           event.setInstant(i);
           event.setSocieteID(id);
           event.setValue(100 +Math.random()*1000);
           return event;
        }));
       return  Flux.zip(interval,events).map(Tuple2::getT2);
    }
}


