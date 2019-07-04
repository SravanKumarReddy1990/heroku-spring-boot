package com.github.britter.springbootherokudemo.model;

import org.springframework.web.multipart.MultipartFile;

public class RegistrationForm {
private String usernamesignup;
private String emailsignup;
private MultipartFile [] albums;
private String passwordsignup;
public String getUsernamesignup() {
	return usernamesignup;
}
public void setUsernamesignup(String usernamesignup) {
	this.usernamesignup = usernamesignup;
}
public String getEmailsignup() {
	return emailsignup;
}
public void setEmailsignup(String emailsignup) {
	this.emailsignup = emailsignup;
}
public String getPasswordsignup() {
	return passwordsignup;
}
public void setPasswordsignup(String passwordsignup) {
	this.passwordsignup = passwordsignup;
}
public MultipartFile[] getAlbums() {
return albums;
}
public void setAlbums(MultipartFile[] albums) {
this.albums = albums;
}
 
}
