package tn.talan.academyApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.talan.academyApp.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
