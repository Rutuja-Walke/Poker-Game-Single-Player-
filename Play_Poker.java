import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Card implements Comparable<Card> {

    public int suit;
    public int rank;

    @Override
    public int compareTo(Card obj) {

        if(this.rank == (obj.rank)) 
            return 0;
        
        else if(this.rank > (obj.rank)) 
            return 1;

        else    
            return -1;
    }
}

class Deck {

    private final int DECK_SIZE = 52;
    private final int SHUFFLE = 2000;
    private final int HAND_CARDS = 5;
    private int restOfDeck = 6;

    Card[] deck = new Card[DECK_SIZE];
    Random r = new Random();

    //fill the deck with cards
    public void fillDeck() {
        int count = 0;

        for(int suit = 1; suit <= 4; suit++) {
            for(int rank = 1; rank <= 13; rank++) {

                deck[count] = new Card();
                deck[count].suit = suit;
                deck[count].rank = rank;
                count++;

            }
        }

    }

    //shuffle the deck
    public void shuffle() {
        
        for(int i=0; i < SHUFFLE; i++) {
            int num1 = r.nextInt(DECK_SIZE);
            int num2 = r.nextInt(DECK_SIZE);

            Card temp = deck[num1];
            deck[num1] = deck[num2];
            deck[num2] = temp;
        }
    }


    // picks 5 cards
    public Card[] pick() {
        Card[] hand = new Card[HAND_CARDS];

        for(int i=0; i<HAND_CARDS; i++) {
            hand[i] = deck[i];
        }

        return hand;
    }

    //in case of redraw cards
    public Card redraw() {
        Card newCard = deck[restOfDeck];
        restOfDeck++;

        return newCard;
    }

    //refresh index to 6 after every new game
    public void refreshDeckPosition() {
        restOfDeck = 6; 
    }
}


class Player {

    //pick 5 cards from deck
    public Card[] draw(Deck deck) {

        // Card[] hand = deck.pick();
        return (deck.pick());
    }


    //switch card with a new card
    public Card redraw(int count, Deck deck) {
        // Card card = deck.redraw();
        // return card;

        return (deck.redraw());
    }
}


class Game {
    private final int HAND_CARDS = 5; // Hand size
    private int again = 1;

    Scanner sc = new Scanner(System.in);
    Deck deck = new Deck();
    Player player = new Player();

    Card[] hand;


    //play game
    public void play() {

        while(again == 1) {

            deck.fillDeck();
            deck.shuffle();

            //player draws cards
            hand = player.draw(deck);
            Arrays.sort(hand);

            //redraw cards
            this.checkHand();
            hand = this.redraw();

            this.checkHand();

            Arrays.sort(hand);
            this.evaluate();

            //play again?
            this.again();
        }

        System.out.println("Thank you for Playing!!!");
    }


    public void checkHand() {

        for(int i=0; i< HAND_CARDS ; i++) {
            this.display(hand[i]);
        }
    }

    //does the player wants to redraw the card
    public Card[] redraw() {

        for(int i=0; i<5; i++) {

            System.out.println("Redraw card " + (i+1) + "?" + " ( 1 for yes, 0 for NO!)");

            int ans = sc.nextInt();
            if(ans == 1) {
                hand[i] = player.redraw(i, deck);
            }
        }
    
            deck.refreshDeckPosition();
            return hand;
    }


       //evaluates the cards picked
    public void evaluate() {

        if(this.royalFlush() == 1)  System.out.println("It's a Royal Flush!");
        else if(this.straightFlush() == 1)  System.out.println("It's a Straight Flush!");
        else if(this.fourOfaKind() == 1)  System.out.println("It's a Four of a Kind!");
        else if(this.fullHouse() == 1)  System.out.println("It's a Full House!");
        else if(this.flush() == 1)  System.out.println("It's a Flush!");
        else if(this.Straight() == 1)  System.out.println("It's a Straight!");
        else if(this.threeOfaKind() == 1)  System.out.println("It's a Three of a Kind!");
        else if(this.twoPairs() == 1)  System.out.println("It's Two pairs!");
        else if(this.Pair() == 1)  System.out.println("It's a Pair!");
        else System.out.println("Your HIghest Card is " + (this.highCard()));

    }


    public void again() {
        System.out.println("Play Again? ( 1 for yes, 0 for NO!)");
        again = sc.nextInt();
    }

    //to display the cards in hand
    public void display(Card card) {

        if (card.rank == 1) {
			System.out.print("Ace of ");
		}
		if (card.rank == 2) {
			System.out.print("Two of ");
		}
		if (card.rank == 3) {
			System.out.print("Three of ");
		}
		if (card.rank == 4) {
			System.out.print("Four of ");
		}
		if (card.rank == 5) {
			System.out.print("Five of ");
		}
		if (card.rank == 6) {
			System.out.print("Six of ");
		}
		if (card.rank == 7) {
			System.out.print("Seven of ");
		}
		if (card.rank == 8) {
			System.out.print("Eight of ");
		}
		if (card.rank == 9) {
			System.out.print("Nine of ");
		}
		if (card.rank == 10) {
			System.out.print("Ten of ");
		}
		if (card.rank == 11) {
			System.out.print("Jack of ");
		}
		if (card.rank == 12) {
			System.out.print("Queen of ");
		}
		if (card.rank == 13) {
			System.out.print("King of ");
		}
		if (card.suit == 1) {
			System.out.print("Spades");
			System.out.println();
		}
		if (card.suit == 2) {
			System.out.print("Hearts");
			System.out.println();
		}
		if (card.suit == 3) {
			System.out.print("Diamonds");
			System.out.println();
		}
		if (card.suit == 4) {
			System.out.print("Clubs");
			System.out.println();
		}
    }

    
    public int royalFlush() {

        if(hand[0].rank == 1 && hand[1].rank == 10 && hand[2].rank == 11 && hand[3].rank == 12 && hand[4].rank == 13)
            return 1;
        
        else
            return 0;
    }

    public int straightFlush() {

        for(int i=1; i<5; i++) {
            if(hand[0].suit != hand[i].suit)
                return 0;
        }

        for(int i=1; i<5; i++) {
            if(hand[i-1].rank != (hand[i].rank - 1))
                return 0;
        }

        return 1;
    }

    public int flush() {

        for(int i=1; i<5; i++) {
            if(hand[0].suit != hand[i].suit)
                return 0;
        }

        return 1;

    }

    public int fourOfaKind() {
        if(hand[0].rank != hand[3].rank && hand[1].rank != hand[4].rank)
            return 0;
        
        else 
            return 1;
    }

    public int threeOfaKind() {
        if(hand[0].rank == hand[2].rank || hand[2].rank == hand[4].rank || hand[1].rank == hand[3].rank)
            return 1;
        
        else return 0;
    }

    public int twoPairs() {
        int count=0;

        for(int i=1; i<5; i++) {
            if(hand[i-1].rank == hand[i].rank)
                count++;
        }

        if(count == 2)  return 1;
        else return 0;
    }

    public int Pair() {
        int count=0;

        for(int i=1; i<5; i++) {
            if(hand[i-1].rank == hand[i].rank)
                count++;
        }

        if(count == 1)  return 1;
        else return 0;
    }

    public int Straight() {
     
        for(int i=1; i<5; i++) {
            if(hand[i-1].rank != (hand[i].rank - 1))
                return 0;
        }

        return 1;
    }

    public int fullHouse() {
        int count=0;

        //pair
        for(int i=1; i<5; i++) {
            if(hand[i-1].rank == hand[i].rank)
                count++;
        }

        int count2=0;
        //three of a kind
        if(hand[0].rank == hand[2].rank || hand[2].rank == hand[4].rank || hand[1].rank == hand[3].rank)
            count2++;

        if(count==1 && count2==1)
            return 1;
        
        else return 0;
    }

    public int highCard() {
        int highCard = 0;

        for(int i=0; i<5; i++) {
            if(hand[i].rank > highCard)
                highCard = hand[i].rank;
        }

        return highCard;        
    }

 

}


public class Play_Poker {

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}

