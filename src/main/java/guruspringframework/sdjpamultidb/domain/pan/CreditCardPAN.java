package guruspringframework.sdjpamultidb.domain.pan;

import guruspringframework.sdjpamultidb.domain.CreditCardConverter;
import jakarta.persistence.*;

@Entity
public class CreditCardPAN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CreditCardConverter.class)
    private String creditCardNumber;
}
