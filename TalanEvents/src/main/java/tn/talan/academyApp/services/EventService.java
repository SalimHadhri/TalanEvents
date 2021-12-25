package tn.talan.academyApp.services;

import java.security.GeneralSecurityException;
import java.util.List;

import javax.mail.MessagingException;

import tn.talan.academyApp.dtos.EventDto;

public interface EventService {

	public EventDto addEvent(EventDto evenetDto);

	public void sendMail(String toEmail, String subject, String body)
			throws GeneralSecurityException, MessagingException;

	public EventDto annulerEventCretated(Long idEvent, Long idUser) throws GeneralSecurityException, MessagingException;

	public List<EventDto> findAllEvents();

	public EventDto findEventById(Long id);


	public EventDto createEvent(EventDto evenetDto, Long idUser) throws GeneralSecurityException, MessagingException;

	public List<EventDto> findEventsCreated(Long id);

	public EventDto findEventByName(String nameEvent);

	public List<EventDto> SearchEventsByName(String nameEvent);

}
