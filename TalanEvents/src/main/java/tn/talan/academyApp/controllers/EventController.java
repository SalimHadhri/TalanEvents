package tn.talan.academyApp.controllers;

import java.security.GeneralSecurityException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.talan.academyApp.dtos.EventDto;
import tn.talan.academyApp.dtos.UserDto;
import tn.talan.academyApp.services.EventService;
import tn.talan.academyApp.services.UserEventParticipationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	public EventService eventService;

	@Autowired
	private UserEventParticipationService eventParticipationService;

	@PutMapping(value = "/annulerEventCretated/{idUser}/{idEvent}")
	public EventDto annulerEvenementCretated(@PathVariable Long idUser, @PathVariable Long idEvent) throws GeneralSecurityException, MessagingException {
		return eventService.annulerEventCretated(idUser,idEvent);
	}

	@GetMapping(value = "/eventsParticipated/{id}")
	public List<EventDto> findEventsParticipatedByUserId(@PathVariable Long id) {
		return eventParticipationService.findEventParticipated(id);

	}

	@PostMapping(value = "/subscribeEvent/{idUser}/{idEvent}")
	public EventDto subscribeEvent(@PathVariable Long idUser, @PathVariable Long idEvent) {

		return eventParticipationService.subscribeEvent(idUser, idEvent);
	}

	@PostMapping(value = "/CreateEvent/{idUser}")
	public EventDto CreateEvent(@PathVariable Long idUser, @RequestBody EventDto eventDto)
			throws GeneralSecurityException, MessagingException {

		return eventService.createEvent(eventDto, idUser);
	}

	@GetMapping(value = "/eventsCreated/{id}")
	public List<EventDto> findEventsCreated(@PathVariable Long id) {
		return eventService.findEventsCreated(id);

	}
	
	
	@GetMapping(value = "/allEvents")
	public List<EventDto> allEvents() {
		return eventService.findAllEvents() ;

	}
	
	@GetMapping(value = "/SearchEventsByName/{name}")
	public List<EventDto> findEventsByName(@PathVariable String name) {
	return eventService.SearchEventsByName(name);
	}
	
	@GetMapping(value = "/topThreeUserParticipation")
	public List<UserDto> topThreeUserParticipation() {
	return eventParticipationService.topThreeDeParticipation() ;
	}
	
	@GetMapping(value = "/topThreeUserCreation")
	public List<UserDto> topThreeUserCreation() {
	return eventParticipationService.topThreeUserCreators() ;
	}
	
	@GetMapping(value = "/tauxPresenceEvent/{id}")
	public Long tauxPresenceEvent(@PathVariable Long id) {
	return eventParticipationService.tauxDePresenceParEvenement(id) ;
	}
	
	@GetMapping(value = "/topThreeEventParticipation")
	public List<EventDto> topThreeEventParticipation() {
	return eventParticipationService.topThreePresenceParEvenement() ;
	}
	
	

}
