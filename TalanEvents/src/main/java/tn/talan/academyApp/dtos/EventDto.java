package tn.talan.academyApp.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EventDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long eventId;
	private String nameEvent;
	private String descriptionEvent;
	private String lieuEvent;
	private Date dateDebutEvent;
	private Date dateFinEvent;
	private int nombreParticipantsEvent;
	private Boolean annulée = Boolean.FALSE;
	private String pathImage;
	private String status = "validée";
	@JsonIgnore
	private Set<CritereDto> criteresEvent = new HashSet<>();
	@JsonIgnore
	private Set<UserEventParticipationDto> userEventParticipation;
	private UserDto userCreator;

	public EventDto() {
	}

	public EventDto(String nameEvent, String descriptionEvent, String lieuEvent, Date dateDebutEvent, Date dateFinEvent,
			int nombreParticipantsEvent, boolean annulée, String pathImage) {
		super();
		this.nameEvent = nameEvent;
		this.descriptionEvent = descriptionEvent;
		this.lieuEvent = lieuEvent;
		this.dateDebutEvent = dateDebutEvent;
		this.dateFinEvent = dateFinEvent;
		this.nombreParticipantsEvent = nombreParticipantsEvent;
		this.annulée = annulée;
		this.pathImage = pathImage;
	}

	public EventDto(Long eventId, String nameEvent, String descriptionEvent, String lieuEvent, Date dateDebutEvent,
			Date dateFinEvent, int nombreParticipantsEvent, boolean annulée, String pathImage,
			Set<CritereDto> criteresEvent, String status, UserDto userCreator) {
		this.eventId = eventId;
		this.nameEvent = nameEvent;
		this.descriptionEvent = descriptionEvent;
		this.lieuEvent = lieuEvent;
		this.dateDebutEvent = dateDebutEvent;
		this.dateFinEvent = dateFinEvent;
		this.nombreParticipantsEvent = nombreParticipantsEvent;
		this.annulée = annulée;
		this.pathImage = pathImage;
		this.criteresEvent = criteresEvent;
		this.status = status;
		this.userCreator = userCreator;
	}

	public EventDto(String nameEvent, String descriptionEvent, String lieuEvent, Date dateDebutEvent, Date dateFinEvent,
			int nombreParticipantsEvent, boolean annulée, String pathImage, UserDto userCreator) {
		super();
		this.nameEvent = nameEvent;
		this.descriptionEvent = descriptionEvent;
		this.lieuEvent = lieuEvent;
		this.dateDebutEvent = dateDebutEvent;
		this.dateFinEvent = dateFinEvent;
		this.nombreParticipantsEvent = nombreParticipantsEvent;
		this.annulée = annulée;
		this.pathImage = pathImage;
		this.userCreator = userCreator;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}

	public String getDescriptionEvent() {
		return descriptionEvent;
	}

	public void setDescriptionEvent(String descriptionEvent) {
		this.descriptionEvent = descriptionEvent;
	}

	public Date getDateDebutEvent() {
		return dateDebutEvent;
	}

	public void setDateDebutEvent(Date dateDebutEvent) {
		this.dateDebutEvent = dateDebutEvent;
	}

	public Date getDateFinEvent() {
		return dateFinEvent;
	}

	public void setDateFinEvent(Date dateFinEvent) {
		this.dateFinEvent = dateFinEvent;
	}

	public int getNombreParticipantsEvent() {
		return nombreParticipantsEvent;
	}

	public void setNombreParticipantsEvent(int nombreParticipantsEvent) {
		this.nombreParticipantsEvent = nombreParticipantsEvent;
	}

	public boolean isAnnulée() {
		return annulée;
	}

	public void setAnnulée(boolean annulée) {
		this.annulée = annulée;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public Set<CritereDto> getCriteresEvent() {
		return criteresEvent;
	}

	public void setCriteresEvent(Set<CritereDto> criteresEvent) {
		this.criteresEvent = criteresEvent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserDto getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(UserDto userCreator) {
		this.userCreator = userCreator;
	}

	public String getLieuEvent() {
		return lieuEvent;
	}

	public void setLieuEvent(String lieuEvent) {
		this.lieuEvent = lieuEvent;
	}

	public Boolean getAnnulée() {
		return annulée;
	}

	public void setAnnulée(Boolean annulée) {
		this.annulée = annulée;
	}

	public Set<UserEventParticipationDto> getUserEventParticipation() {
		return userEventParticipation;
	}

	public void setUserEventParticipation(Set<UserEventParticipationDto> userEventParticipation) {
		this.userEventParticipation = userEventParticipation;
	}

}
