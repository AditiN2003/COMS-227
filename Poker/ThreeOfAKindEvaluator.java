package hw4;

import api.Card;

/**
 * Evaluator for a hand containing (at least) three cards of the same rank.
 * The number of cards required is three.
 * 
 * The name of this evaluator is "Three of a Kind".
 * 
 * @author AditiN
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class ThreeOfAKindEvaluator extends AbstractEvaluator
{
	/**
	 * Constructs the evaluator.
	 * @param ranking
	 *   ranking of this hand
	 * @param handSize
	 *   number of cards in a hand
	 */
	
	  public ThreeOfAKindEvaluator(int ranking, int handSize)
	  {
	    super(ranking,handSize,"Three of a Kind",3);
	  }
 
	@Override
	public boolean canSatisfy(Card[] mainCards) {
		// TODO Auto-generated method stub
		return (mainCards.length==cardsRequired() &&mainCards[0].compareToIgnoreSuit(mainCards[1])==0 &&mainCards[1].compareToIgnoreSuit(mainCards[2])==0);
	}
}
