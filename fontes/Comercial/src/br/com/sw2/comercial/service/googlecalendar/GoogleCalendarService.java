package br.com.sw2.comercial.service.googlecalendar;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.AclRule;
import com.google.api.services.calendar.model.AclRule.Scope;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

public class GoogleCalendarService {

	private static final String PRIMARY = "primary";
	private static final String EMAIL_PROPRIO = "odontodo@gmail.com";
	private static final String EMAIL_TERCEIRO = "paciente.odonto@gmail.com";
	static final String EMAIL_SERVICE_ACCOUNT = "776101340018-elojl84r7lu5rvbq55bdajk7mvqfkdqb@developer.gserviceaccount.com";
	static final String PATH = "d:\\SW2\\OdonTO-DO\\fontes\\Comercial\\src\\main\\resources\\credentials\\OdonTO-DO-6368a879e485.p12";

	public static void main(String[] args) {
		com.google.api.services.calendar.Calendar client = setUp();

		// Criar e alterar evento na propria agenda
		String eventoId = criarEvento(client, EMAIL_PROPRIO);
		alterarEvento(client, eventoId);
		
		// Criar e alterar evento na agenda de terceiro 
		eventoId = criarEvento(client, EMAIL_TERCEIRO); 
		alterarEvento(client, eventoId);

		recuperarEventos(client);

	}

	private static Calendar setUp() {
		GoogleCredential credential = null;

		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		HttpTransport httpTransport = null;

		try {
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			credential = new GoogleCredential.Builder()
					.setTransport(httpTransport)
					.setJsonFactory(jsonFactory)
					.setServiceAccountId(EMAIL_SERVICE_ACCOUNT)
					.setServiceAccountPrivateKeyFromP12File(new File(PATH))
					.setServiceAccountScopes(Arrays.asList(CalendarScopes.CALENDAR,
									CalendarScopes.CALENDAR_READONLY)).build();
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
		}

		// Calendar client
		return new com.google.api.services.calendar.Calendar.Builder(
				httpTransport, jsonFactory, credential).setApplicationName(
				"OdonTO-DO").build();

	}

	
	private static String criarEvento(Calendar client, String email) {

		Event event = new Event();

		event.setSummary("Canal Paciente Y");

		ArrayList<EventAttendee> attendees = new ArrayList<EventAttendee>();
		attendees.add(new EventAttendee().setEmail(email));
		event.setAttendees(attendees);

		LocalDateTime startDate = LocalDateTime.now();
		LocalDateTime endDate = LocalDateTime.of(startDate.toLocalDate(),
				startDate.toLocalTime().plus(1, ChronoUnit.HOURS));
		Date start = Date.from(startDate.atZone(ZoneId.systemDefault())
				.toInstant());
		Date end = Date
				.from(endDate.atZone(ZoneId.systemDefault()).toInstant());
		event.setStart(new EventDateTime().setDateTime(new DateTime(start)));
		event.setEnd(new EventDateTime().setDateTime(new DateTime(end)));

		Event createdEvent = null;
		try {
			createdEvent = client.events().insert(PRIMARY, event).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(createdEvent.toString());

		return createdEvent.getId();

	}

	private static void alterarEvento(Calendar client, String eventoId) {

		Event updatedEvent = null;
		try {
			// First retrieve the event from the API.
			Event event = client.events().get(PRIMARY, eventoId).execute();

			event.setDescription("Molar inferior esquerdo");
			updatedEvent = client.events()
					.update(PRIMARY, event.getId(), event).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Print the updated event.
		System.out.println(updatedEvent.toString());

	}

	private static void recuperarEventos(Calendar client) {

		String pageToken = null;

		do {
			Events events = null;
			try {
				events = client.events().list(PRIMARY).setPageToken(pageToken).execute();
			} catch (IOException e) {
				e.printStackTrace();
			}

			List<Event> items = events.getItems();

			for (Event event : items) {
				System.out.println(event.getSummary());
				System.out.println("Inicio: " + event.getStart().getDateTime());
				System.out.println("Fim: " + event.getEnd().getDateTime());
				System.out.println("---------------------------------------------------");
			}

			pageToken = events.getNextPageToken();

		} while (pageToken != null);

	}
	
	/**
	 * Altera o proprietário da agenda, permitindo que o usuário do gmail 
	 * tenha acesso a agenda do Service Account
	 * @param client
	 */
	
	@SuppressWarnings("unused")
	private void alterarDonoAgenda(Calendar client) {
		AclRule rule = new AclRule();
		Scope scope = new Scope();

		scope.setType("user");
		scope.setValue(EMAIL_PROPRIO);
		rule.setScope(scope);
		rule.setRole("owner");

		try {
			client.acl().insert(PRIMARY, rule).execute();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
