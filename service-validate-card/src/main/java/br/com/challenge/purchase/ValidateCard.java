package br.com.challenge.purchase;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ValidateCard {

    public static void main(String[] args) {
        var validateService = new ValidateCard();
        try (var service = new KafkaService<>(ValidateCard.class.getSimpleName(),
                "CHALLENGE_NEW_CARD_PURCHASE",
                validateService::parse,
                Card.class,
                Map.of())) {
            service.run();
        }
    }

    private final KafkaDispatcher<Card> orderDispatcher = new KafkaDispatcher<>();

    private void parse(ConsumerRecord<String, Card> record) throws ExecutionException, InterruptedException {
        System.out.println("------------------------------------------");
        System.out.println("Validando dados do Cartão");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        var card = record.value();

        if (isFraud(card)) {
            //verificar o CVV e validade ??
            System.out.println("Dados inválidos!!!!!" + card);
            orderDispatcher.send("CHALLENGE_NEW_CARD_PURCHASE_REJECTED", card.getNome(), card);
        } else {
            System.out.println("Approved: " + card);
            orderDispatcher.send("CHALLENGE_NEW_CARD_PURCHASE_APPROVED", card.getNome(), card);
        }

    }

    private boolean isFraud(Card card) {
        //teste
        return true;
    }

}
