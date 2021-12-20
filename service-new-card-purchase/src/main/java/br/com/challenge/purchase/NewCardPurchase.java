package br.com.challenge.purchase;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewCardPurchase {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (var cardDispatcher = new KafkaDispatcher<Card>()) {
            try (var validateCard = new KafkaDispatcher<String>()) {
                var numero = "8475 4652 8454 2155";
                var nome = Math.random() + " teste Silva";

                for (var i = 0; i < 10; i++) {
                    var cvv = 875;
                    var order = new Card(nome, cvv, numero);
                    cardDispatcher.send("CHALLENGE_NEW_CARD_PURCHASE", numero, order);
                }
            }
        }
    }
}
