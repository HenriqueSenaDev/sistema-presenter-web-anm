package gov.edu.anm.presenter.api.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

	Optional<Event> findByJoinCode(String code);
}
