package hw4;

import java.util.ArrayList;

import api.Card;
import api.Hand;

/**
 * Evaluator for a generalized full house.  The number of required
 * cards is equal to the hand size.  If the hand size is an odd number
 * n, then there must be (n / 2) + 1 cards of the matching rank and the
 * remaining (n / 2) cards must be of matching rank. In this case, when constructing
 * a hand, the larger group must be listed first even if of lower rank
 * than the smaller group</strong> (e.g. as [3 3 3 5 5] rather than [5 5 3 3 3]).
 * If the hand size is even, then half the cards must be of matching 
 * rank and the remaining half of matching rank.  Any group of cards,
 * all of which are the same rank, always satisfies this
 * evaluator.
 * 
 * The name of this evaluator is "Full House".
 * 
 * @author AditiN
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class FullHouseEvaluator extends AbstractEvaluator
{
	/**
	   * Constructs the evaluator.
	   * @param ranking
	   *   ranking of this hand
	   * @param handSize
	   *   number of cards in a hand
	   */
	  public FullHouseEvaluator(int ranking, int handSize)
	  {
	    super(ranking, handSize,"Full House",handSize);
	  }
	
	@Override
	public boolean canSatisfy(Card[] mainCards) {
		boolean word = false;
		if (mainCards.length==cardsRequired()) {
			word = true;
			for (int i = 1; i<mainCards.length;i++) {
				int temp = mainCards[0].getRank();
				if (temp!=mainCards[i].getRank()) {
					word = false;
				}
			}
			if (word==false) {
				
				int tempCard1=mainCards[0].getRank();
				int tempCard2=0;
				int tempCardCount1=0;
				int tempCardCount2=0;
				
				for (int j = 0;j<mainCards.length;j++) {
					if (mainCards[j].getRank()!=tempCard1) {
						tempCard2=mainCards[j].getRank();
						break;
					}
				}
				for (int k = 0;k<mainCards.length;k++) {
					if(mainCards[k].getRank()==tempCard1) {
						tempCardCount1++;
					}
					else if(mainCards[k].getRank()==tempCard2) {
						tempCardCount2++;
					}
				}
				
				if (tempCardCount1+tempCardCount2==handSize()) {
					word=true;
				}
			}
				
		}
		return word; 
	
		
	}
	 
	

}
