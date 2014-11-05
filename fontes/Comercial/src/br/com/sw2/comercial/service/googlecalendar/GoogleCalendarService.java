package br.com.sw2.comercial.service.googlecalendar;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;


public class GoogleCalendarService {
	
	private static final String PRIMARY = "primary";
	private static final String EMAIL_TESTE = "odontodo@gmail.com";
	static final String EMAIL_SERVICE_ACCOUNT = "776101340018-elojl84r7lu5rvbq55bdajk7mvqfkdqb@developer.gserviceaccount.com";
	static final String PATH = "d:\\SW2\\OdonTO-DO\\fontes\\Comercial\\src\\main\\resources\\credentials\\OdonTO-DO-6368a879e485.p12";
	
	public static void main(String[] args) {
		com.google.api.services.calendar.Calendar client = setUp();
		String eventoId = criarEvento(client);
		System.out.println(eventoId);
		alterarEvento(client, eventoId);
	}
	
	private static Calendar setUp() {
		GoogleCredential credential = null;
		
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		HttpTransport httpTransport = null;
		
		try {
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			List<String> scopes = new ArrayList<String>();
			scopes.add(CalendarScopes.CALENDAR);
			scopes.add(CalendarScopes.CALENDAR_READONLY);
			credential = new GoogleCredential.Builder()
			    .setTransport(httpTransport)
			    .setJsonFactory(jsonFactory)
			    .setServiceAccountId(EMAIL_SERVICE_ACCOUNT)
			    .setServiceAccountPrivateKeyFromP12File(new File(PATH))
			    .setServiceAccountScopes(scopes)
			    .build();
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
		}
		
		// Calendar client
		return new com.google.api.services.calendar.Calendar.Builder(
				httpTransport, jsonFactory, credential).setApplicationName("OdonTO-DO").build();
	    
	}
	
/*	private static void recuperarAgenda(Calendar client) {
		
		String pageToken = null;
		
		do {
		  CalendarList calendarList = null;
		  
		try {
			calendarList = client.calendarList().list().setPageToken(pageToken).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		  List<CalendarListEntry> items = calendarList.getItems();

		  for (CalendarListEntry calendarListEntry : items) {
		    System.out.println(calendarListEntry.getSummary());
		  }
		  pageToken = calendarList.getNextPageToken();
		} while (pageToken != null);
		
	}*/
	
	private static String criarEvento(Calendar client) {
		
		Event event = new Event();

		event.setSummary("Canal Paciente X");

		ArrayList<EventAttendee> attendees = new ArrayList<EventAttendee>();
		attendees.add(new EventAttendee().setEmail(EMAIL_TESTE));
		event.setAttendees(attendees);

		Date startDate = new Date();
		Date endDate = new Date(startDate.getTime() + 3600000);
		DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
		event.setStart(new EventDateTime().setDateTime(start));
		DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
		event.setEnd(new EventDateTime().setDateTime(end));

		Event createdEvent = null;
		try {
			createdEvent = client.events().insert(PRIMARY, event).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return createdEvent.getId();
		
	}
	
	private static void alterarEvento(Calendar client, String eventoId) {
		
		Event updatedEvent = null;
		try {
			// First retrieve the event from the API.
			Event event = client.events().get(PRIMARY, eventoId).execute();
			
			event.setDescription("Molar inferior esquerdo");
			updatedEvent = client.events().update(PRIMARY, event.getId(), event).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Print the updated event.
		System.out.println(updatedEvent.toString());
		
	}

}
