package tn.talan.academyApp.dtos;

import java.io.Serializable;

import tn.talan.academyApp.entities.UserEventParticipationkey;

public class UserEventParticipationDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private UserEventParticipationkey id;
	private UserDto user;

	private EventDto event;

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public EventDto getEvent() {
		return event;
	}

	public void setEvent(EventDto event) {
		this.event = event;
	}

	public UserEventParticipationkey getId() {
		return id;
	}

	public void setId(UserEventParticipationkey id) {
		this.id = id;
	}

}
