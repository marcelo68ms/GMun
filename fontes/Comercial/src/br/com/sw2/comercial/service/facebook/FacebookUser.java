package br.com.sw2.comercial.service.facebook;

import com.google.gson.JsonObject;

public class FacebookUser {

	private static final String EMPTY = "-";
	private Long id;
	private String firstName;
	private Integer timezone;
	private String email;
	private Boolean verified;
	private String middleName;
	private String gender;
	private String lastName;
	private String link;
	private String locale;
	private String name;
	private String updatedTime;
	private String birthday;
	private String hometown;
	private String location;

	public FacebookUser(JsonObject jsonUsuario) {

		id = jsonUsuario.get("id").getAsLong();
		firstName = jsonUsuario.get("first_name").getAsString();
		timezone = jsonUsuario.get("timezone").getAsInt();
		email = jsonUsuario.get("email").getAsString();
		verified = jsonUsuario.get("verified").getAsBoolean();
		middleName = jsonUsuario.get("middle_name").getAsString();
		gender = jsonUsuario.get("gender").getAsString();
		lastName = jsonUsuario.get("last_name").getAsString();
		link = jsonUsuario.get("link").getAsString();
		locale = jsonUsuario.get("locale").getAsString();
		name = jsonUsuario.get("name").getAsString();
		updatedTime = jsonUsuario.get("updated_time").getAsString();
		birthday = jsonUsuario.get("birthday").getAsString();
		hometown = (jsonUsuario.get("hometown") != null?jsonUsuario.get("hometown").getAsJsonObject().get("name").getAsString():EMPTY);
		location = (jsonUsuario.get("location") != null?jsonUsuario.get("location").getAsJsonObject().get("name").getAsString():EMPTY);
		
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getTimezone() {
		return timezone;
	}

	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Override
	public String toString() {
		return "FacebookUser [id=" + id + ", firstName=" + firstName
				+ ", timezone=" + timezone + ", email=" + email + ", verified="
				+ verified + ", middleName=" + middleName + ", gender="
				+ gender + ", lastName=" + lastName + ", link=" + link
				+ ", locale=" + locale + ", name=" + name + ", updatedTime="
				+ updatedTime + ", birthday=" + birthday + ", hometown="
				+ hometown + ", location=" + location + "]";
	}

}
