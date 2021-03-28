package pl.zzpj2021.solid.isp.contactbook.solution;

public class Dialler extends Contact implements DiallerInterface{

    private String telephone;

    public Dialler(String name, String address, String telephone) {
        super(name, address);
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public void makeCall() {

        // call using telephone
    }
}
