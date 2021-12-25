package tn.talan.academyApp.services;

import java.util.List;

import tn.talan.academyApp.dtos.EventDto;
import tn.talan.academyApp.dtos.UserDto;

public interface UserEventParticipationService {

	public List<EventDto> findEventParticipated(Long idUser);

	public EventDto subscribeEvent(Long idUser, Long idEvent);

	public List<UserDto> findUserParticipated(Long idEvent);

	public EventDto annulerUserEventParticipation(Long idUser, Long idEvent);
	
	public UserDto topParticipation(List<UserDto> allUsers) ;

	public int maxIndiceList(List<Long> list) ;

	public List<UserDto> topThreeDeParticipation() ;
	
	public UserDto topUserCreations(List<UserDto> allUsers) ;
	
	public List<UserDto> topThreeUserCreators() ;
	
	//pourcentage de remplissage de l'evenement
	public Long tauxDePresenceParEvenement(Long idEvent);

	public List<EventDto> topThreePresenceParEvenement() ;
	
	public EventDto topEventTauxParicipation(List<EventDto> allEvents) ;

	
		

}
