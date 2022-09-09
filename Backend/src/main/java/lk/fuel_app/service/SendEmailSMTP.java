package lk.fuel_app.service;

public interface SendEmailSMTP {

    void sendEmail(String emailTo, String emailSubject, String emailText);

}
