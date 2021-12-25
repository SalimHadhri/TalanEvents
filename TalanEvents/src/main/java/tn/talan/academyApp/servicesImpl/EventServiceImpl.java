package tn.talan.academyApp.servicesImpl;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.mail.util.MailSSLSocketFactory;

import tn.talan.academyApp.dtos.EventDto;
import tn.talan.academyApp.dtos.UserDto;
import tn.talan.academyApp.entities.Event;
import tn.talan.academyApp.entities.User;
import tn.talan.academyApp.repositories.EventRepository;
import tn.talan.academyApp.repositories.UserRepository;
import tn.talan.academyApp.services.EventService;
import tn.talan.academyApp.services.UserEventParticipationService;
import tn.talan.academyApp.services.UserService;

@Service
@Transactional
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserEventParticipationService userEventParticipationService;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public EventDto addEvent(EventDto eventDto) {
		Event eventToAdd = modelMapper.map(eventDto, Event.class);
		Event eventAdded = eventRepository.save(eventToAdd);
		return modelMapper.map(eventAdded, EventDto.class);
	}

	// Function used to send an email from a predefined email to a destination
	public void sendMail(String toEmail, String subject, String body)
			throws GeneralSecurityException, MessagingException {
		final String username = "TalanEvent@gmail.com";
		final String password = "TalanEvent123";
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.localhost", "localhost");
		props.put("mail.smtp.host", "smtp.gmail.com");//
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.user", "TalanEvent@gmail.com");
		props.put("mail.smtp.password", "TalanEvent123");
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustedHosts(new String[] { "smtp.gmail.com" });
		props.put("mail.smtp.ssl.socketFactory", sf);
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		session.setDebug(true);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("hadhrisalim10@gmail.com"));// formBean.getString("fromEmail")
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		message.setSubject(subject);// formBean.getString(
		message.setText(body);
		Transport.send(message);
	}

	@Override
	public EventDto annulerEventCretated(Long idUser, Long idEvent)
			throws GeneralSecurityException, MessagingException {

		EventDto eventDtoAAnnuler = findEventById(idEvent);

		if (eventDtoAAnnuler.getUserCreator().getUserId() == idUser) {

			eventDtoAAnnuler.setAnnulée(true);

			List<UserDto> usersParticipated = userEventParticipationService.findUserParticipated(idEvent);

			for (UserDto user : usersParticipated) {
				sendMail(user.getEmail(), "Annulation evenement",
						"Evenement " + eventDtoAAnnuler.getNameEvent() + " annulle");
			}
			return addEvent(eventDtoAAnnuler);
		}

		else
			return null;
	}

	@Override
	public List<EventDto> findAllEvents() {
		List<Event> eventsToDisplay = eventRepository.findAll();
		List<EventDto> eventsDtoToDisplay = new ArrayList<>();
		for (int i = 0; i < eventsToDisplay.size(); i++) {
			eventsDtoToDisplay.add(modelMapper.map(eventsToDisplay.get(i), EventDto.class));
		}
		return eventsDtoToDisplay;
	}

	@Override
	public EventDto findEventById(Long id) {
		return modelMapper.map(eventRepository.findById(id).get(), EventDto.class);
	}

	@Override
	public EventDto createEvent(EventDto evenetDto, Long idUser) throws GeneralSecurityException, MessagingException {

		Optional<User> user = userRepository.findById(idUser);

		evenetDto.setUserCreator(modelMapper.map(user.get(), UserDto.class));

		List<UserDto> usersToNotify = userService.findAllUsers();

		for (UserDto userDto : usersToNotify) {

			sendMail(userDto.getEmail(), "New Event : " + evenetDto.getNameEvent(), evenetDto.getDescriptionEvent());
		}
		Event userCreated = eventRepository.save(modelMapper.map(evenetDto, Event.class));

		return modelMapper.map(userCreated, EventDto.class);

	}

	@Override
	public List<EventDto> findEventsCreated(Long id) {

		Optional<User> userCreator = userRepository.findById(id);

		List<Event> eventsCreated = userCreator.get().getEvenementsCreated();
		List<EventDto> eventsCreatedDto = new ArrayList<>();
		if (eventsCreated != null && !eventsCreated.isEmpty()) {
			eventsCreated.stream().forEach(event -> {
				eventsCreatedDto.add(modelMapper.map(event, EventDto.class));

			});

		}

		return eventsCreatedDto;
	}

	@Override
	public List<EventDto> SearchEventsByName(String nameEvent) {

		List<EventDto> listEventTosearch = findAllEvents();

		List<String> eventNames = new ArrayList<>();
		List<String> eventNamesFound = new ArrayList<>();

		for (EventDto dto : listEventTosearch) {
			eventNames.add(dto.getNameEvent());
		}

		for (String name : eventNames) {
			String regex = ".*" + nameEvent + ".*";
			Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(name);

			if (matcher.matches()) {
				eventNamesFound.add(name);
			}
		}

		List<EventDto> eventsFound = new ArrayList<>();

		for (String eventName : eventNamesFound) {

			eventsFound.add(findEventByName(eventName));
		}

		return eventsFound;
	}

	@Override
	public EventDto findEventByName(String nameEvent) {

		List<EventDto> listEventTosearch = findAllEvents();

		for (EventDto eventDto : listEventTosearch) {
			if (eventDto.getNameEvent().equals(nameEvent)) {
				return eventDto;
			}
		}

		return null;
	}
}
