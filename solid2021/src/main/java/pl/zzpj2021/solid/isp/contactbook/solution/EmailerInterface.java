package pl.zzpj2021.solid.isp.contactbook.solution;

public interface EmailerInterface {

    void sendMessage(String subject, String body);
    void sendEmail(String emailAddress, String subject, String body);


}
