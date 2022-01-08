package checkout;

public class CreditCard {
    private String cardCode = "kstn_group8_2021";
    private String owner = "Group 8";
    private String cvvCode = "412";
    private String dateExpired = "1125";

    public CreditCard(String cardCode, String owner, String cvvCode, String dateExpired) {
        this.cardCode = cardCode;
        this.owner = owner;
        this.cvvCode = cvvCode;
        this.dateExpired = dateExpired;
    }

    public String getCardCode() {
        return cardCode;
    }

    public String getOwner() {
        return owner;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public String getDateExpired() {
        return dateExpired;
    }
}
