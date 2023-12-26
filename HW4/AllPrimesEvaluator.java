package hw4;

import api.Card;

/**
 * Evaluator for a hand in which the rank of each card is a prime number.
 * The number of cards required is equal to the hand size. 
 * 
 * The name of this evaluator is "All Primes".
 * 
 * @author AditiN
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class AllPrimesEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   */
  public AllPrimesEvaluator(int ranking, int handSize)
  {
    super(ranking,handSize,"All Primes",handSize);
  }

	@Override
	public boolean canSatisfy(Card[] mainCards) {
		boolean word = false;
		if (mainCards.length==cardsRequired()) {
			word = true;
			for (int i = 0; i<mainCards.length;i++ ) {
				int num = mainCards[i].getRank();
				int temp = 0; 
				for(int j=2;j<=num/2;j++)
				{
					temp=num%j;
				   if(temp==0)
				   {
				      word=false;
				      break;
				   }
				}
			}
		}
		return word;
	}
  
}
