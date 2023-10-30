package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CardGameTest {

    @Test
    public void blackJack() {
        //hit new deck API and get deck_id

        String newDeckURL = "https://www.deckofcardsapi.com/api/deck/new/";
        Response response = RestAssured.given().get(newDeckURL);
        response.prettyPrint();
        System.out.println("status code: " + response.statusCode());

        //let's use JSON path, since this test is not related to our application

        String deckId = response.jsonPath().getString("deck_id");
        System.out.println("deck id:" + deckId);

        //assert that our deck of cards is not shuffled

        boolean shuffled = response.jsonPath().getBoolean("shuffled");
        Assert.assertFalse(shuffled);

        //now we need to shuffle our deck of cards

        String shuffleURL = "https://www.deckofcardsapi.com/api/deck/" + deckId + "/shuffle/";

        response = RestAssured.given().get(shuffleURL);
        response.prettyPrint();
        shuffled = response.jsonPath().getBoolean("shuffled");

        // we need to assert that our deck of cards is shuffled
        Assert.assertTrue(shuffled);

        //now deal 3 cards to 2 students

        String drawUrl = "https://deckofcardsapi.com/api/deck/" + deckId + "/draw/";
        Map<String, Object> params = new HashMap<>();
        params.put("count", 3);

        response = RestAssured.given().params(params).get(drawUrl);
        response.prettyPrint();

        System.out.println("first player's cards: ");
        System.out.println(response.jsonPath().getString("cards[0].value"));
        System.out.println(response.jsonPath().getString("cards[1].value"));
        System.out.println(response.jsonPath().getString("cards[2].value"));

        response = RestAssured.given().params(params).get(drawUrl);
        response.prettyPrint();

        System.out.println("second player's cards: ");
        System.out.println(response.jsonPath().getString("cards[0].value"));
        System.out.println(response.jsonPath().getString("cards[1].value"));
        System.out.println(response.jsonPath().getString("cards[2].value"));

        //verify that we have less than 52 cards
        int remaining = response.jsonPath().getInt("remaining");
        Assert.assertEquals(46, remaining);
    }
}
