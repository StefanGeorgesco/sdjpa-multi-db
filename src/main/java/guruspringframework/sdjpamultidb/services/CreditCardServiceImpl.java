package guruspringframework.sdjpamultidb.services;

import guruspringframework.sdjpamultidb.domain.cardholder.CreditCardHolder;
import guruspringframework.sdjpamultidb.domain.creditcard.CreditCard;
import guruspringframework.sdjpamultidb.domain.pan.CreditCardPAN;
import guruspringframework.sdjpamultidb.repositories.cardholder.CreditCardHolderRepository;
import guruspringframework.sdjpamultidb.repositories.creditcard.CreditCardRepository;
import guruspringframework.sdjpamultidb.repositories.pan.CreditCardPANRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;

    private final CreditCardHolderRepository creditCardHolderRepository;

    private final CreditCardPANRepository creditCardPANRepository;

    @Override
    @Transactional(readOnly = true)
    public CreditCard getCreditCardById(Long id) {

        CreditCard fetchedCard = creditCardRepository.findById(id).orElseThrow();
        CreditCardHolder fetchedCardHolder = creditCardHolderRepository.findByCreditCardId(id).orElseThrow();
        CreditCardPAN fetchedCardPAN = creditCardPANRepository.findByCreditCardId(id).orElseThrow();
        fetchedCard.setFirstName(fetchedCardHolder.getFirstName());
        fetchedCard.setLastName(fetchedCardHolder.getLastName());
        fetchedCard.setZipCode(fetchedCardHolder.getZipCode());
        fetchedCard.setCreditCardNumber(fetchedCardPAN.getCreditCardNumber());

        return fetchedCard;
    }

    @Override
    @Transactional
    public CreditCard saveCreditCard(CreditCard creditCard) {

        CreditCard savedCard = creditCardRepository.save(creditCard);

        creditCardHolderRepository.save(CreditCardHolder
                .builder()
                .firstName(savedCard.getFirstName())
                .lastName(savedCard.getLastName())
                .zipCode(savedCard.getZipCode())
                .creditCardId(savedCard.getId())
                .build());

        creditCardPANRepository.save(CreditCardPAN
                .builder()
                .creditCardNumber(savedCard.getCreditCardNumber())
                .creditCardId(savedCard.getId())
                .build());

        return savedCard;
    }
}
