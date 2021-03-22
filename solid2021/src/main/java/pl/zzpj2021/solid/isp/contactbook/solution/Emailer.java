package pl.zzpj2021.solid.isp.contactbook.solution;

public class Emailer extends Contact implements EmailerInterface{

    private String emailAddress;

    public Emailer(String name, String address, String emailAddress) {
        super(name, address);
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void sendMessage(String subject, String body) {

        sendEmail(emailAddress, subject, body);
    }

    @Override
    public void sendEmail(String emailAddress, String subject, String body) {
        // TODO Auto-generated method stub

    }
}
