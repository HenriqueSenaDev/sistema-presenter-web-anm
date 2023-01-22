package gov.edu.anm.presenter.api.event;

import gov.edu.anm.presenter.api.participation.Participation;
import gov.edu.anm.presenter.api.team.TeamInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Event> findEventById(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.findEventById(id));
    }

    @GetMapping(value = "/code/{code}")
    public ResponseEntity<Event> findByJoinCode(@PathVariable String code) {
        return ResponseEntity.ok().body(eventService.findEventByJoinCode(code));
    }

    @GetMapping
    public ResponseEntity<List<Event>> findAllEvents() {
        return ResponseEntity.ok().body(eventService.findAllEvents());
    }

    @GetMapping(value = "/participations/{id}")
    public ResponseEntity<List<Participation>> findEventParticipations(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.findEventParticipations(id));
    }
    
    @PostMapping
    public ResponseEntity<Event> saveEvent(@RequestBody EventInputDto event) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("").toUriString());
        return ResponseEntity.created(uri).body(eventService.saveEvent(event));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event, @PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.updateEvent(event, id));
    }

    @PutMapping(value = "/{eventId}/teams")
    public ResponseEntity<Event> putTeamInEvent(@RequestBody TeamInputDto team, @PathVariable Long eventId) {
        return ResponseEntity.ok().body(eventService.putTeamInEvent(eventId, team));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("The Event has been deleted.");
    }

}
