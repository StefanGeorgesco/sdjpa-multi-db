package guruspringframework.sdjpamultidb;

import guruspringframework.sdjpamultidb.domain.creditcard.CreditCard;
import guruspringframework.sdjpamultidb.repositories.cardholder.CreditCardHolderRepository;
import guruspringframework.sdjpamultidb.services.CreditCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SdjpaMultiDbApplicationTests {

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    CreditCardHolderRepository creditCardHolderRepository;

    @Test
    void testSaveAndGetCreditCard() {
        String FIRST_NAME = "John";
        String LAST_NAME = "Thompson";
        String ZIP_CODE = "12345";
        String CARD_NUMBER = "1234556";
        String CVV = "123";
        String EXPIRATION_DATE = "12/26";

        CreditCard creditCard = CreditCard.builder()
                .cvv(CVV)
                .expirationDate(EXPIRATION_DATE)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .zipCode(ZIP_CODE)
                .creditCardNumber(CARD_NUMBER)
                .build();

        CreditCard savedCreditCard = creditCardService.saveCreditCard(creditCard);

        assertThat(savedCreditCard).isNotNull();
        assertThat(savedCreditCard.getId()).isNotNull();
        assertEquals(CVV, savedCreditCard.getCvv());
        assertEquals(EXPIRATION_DATE, savedCreditCard.getExpirationDate());
        assertEquals(FIRST_NAME, savedCreditCard.getFirstName());
        assertEquals(LAST_NAME, savedCreditCard.getLastName());
        assertEquals(ZIP_CODE, savedCreditCard.getZipCode());
        assertEquals(CARD_NUMBER, savedCreditCard.getCreditCardNumber());

        CreditCard fetchedCreditCard = creditCardService.getCreditCardById(savedCreditCard.getId());

        assertThat(fetchedCreditCard).isNotNull();
        assertThat(fetchedCreditCard.getId()).isNotNull();
        assertEquals(CVV, fetchedCreditCard.getCvv());
        assertEquals(EXPIRATION_DATE, fetchedCreditCard.getExpirationDate());
        assertEquals(FIRST_NAME, fetchedCreditCard.getFirstName());
        assertEquals(LAST_NAME, fetchedCreditCard.getLastName());
        assertEquals(ZIP_CODE, fetchedCreditCard.getZipCode());
        assertEquals(CARD_NUMBER, fetchedCreditCard.getCreditCardNumber());
    }

}
