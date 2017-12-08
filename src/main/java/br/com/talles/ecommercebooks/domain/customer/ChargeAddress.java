package br.com.talles.ecommercebooks.domain.customer;

public class ChargeAddress extends Address {

    public ChargeAddress() {
        super();
    }

    public ChargeAddress(long id) {
        super(id);
    }

    public ChargeAddress(String alias, long id) {
        super(alias, id);
    }

    public ChargeAddress(String alias, String observation, String publicPlaceType, String publicPlace,
                           String number, String district, String postalCode, String homeType, String city, State state) {
        super(alias, observation, publicPlace, number, district, postalCode, homeType, publicPlaceType, city, state);
    }

    public ChargeAddress(String alias, String observation, String publicPlaceType, String publicPlace,
                           String number, String district, String postalCode, String homeType, String city, State state, long id) {
        super(alias, observation, publicPlace, number, district, postalCode, homeType, publicPlaceType, city, state, id);
    }

}
