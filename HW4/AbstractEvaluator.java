package hw4;
import java.util.ArrayList;
import java.util.Arrays;

import api.Card;
import api.Hand;
import api.IEvaluator;
import util.SubsetFinder;

/**
 * The class AbstractEvaluator includes common code for all evaluator types.
 * 
 * Abstract class initializes ranking, name, handsize, cardsRequired, canSubsetSatisfy, createHand, and getBestHand from the interface
 * since they are the same for all evaluators 
 * 
 * canSatisfy is not in the abstract since it is a little different for all of the classes 
 * 
 * OnePair, ThreeOfAKind, AllPrime, CatchAll, FourOfAKind, FullHouse, and Straight extends AbstractEvaluator 
 * Straight extends StraightFlush since they are similar 
 * 
 * @author AditiN
 */
public abstract class AbstractEvaluator implements IEvaluator
{
	/**
	 *  Ranking of this hand
	 */
	private int ranking;
	
	/**
	 *  Number of cards in a hand
	 */
	private int handSize;
	
	/**
	 * Name of the evaluator
	 */
	private String name;
	
	/**
	 * Number of cards required 
	 */
	private int cardsRequired;
	
	/**
	 * Abstract class for the evaluators. 
	 * @param ranking
	 * 	ranking of this hand
	 * @param handSize
	 * 	number of cards in a hand
	 * @param name
	 * 	name of the evaluator 
	 * @param cardsRequired
	 * 	number of cards required
	 */
	
	protected AbstractEvaluator(int ranking, int handSize,String name, int cardsRequired) {
		this.ranking=ranking;
		this.handSize=handSize;
		this.name = name;
		this.cardsRequired = cardsRequired;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int cardsRequired() {
		return cardsRequired;
	}

	@Override
	public int getRanking() {
		return ranking;
	}

	@Override
	public int handSize() {
		return handSize;
	}

	@Override
	public boolean canSubsetSatisfy(Card[] allCards) {
		boolean word = false; 
		if (allCards.length>=cardsRequired()) {
			ArrayList<int[]> subsets = SubsetFinder.findSubsets(allCards.length,cardsRequired());
			
			Card[] subCards = new Card[cardsRequired()];
			for (int i = 0; i<subsets.size();i++) {
				int arr[] = subsets.get(i);
				for (int j = 0; j<arr.length;j++) {
					subCards[j]=allCards[arr[j]];
				}
				if (canSatisfy(subCards)) {
					word = true;
				}
			}		
		}
		return word;
	}

	@Override
	public Hand createHand(Card[] allCards, int[] subset) {
		Card[] mainCards1= new Card[cardsRequired()];
		Card[] sideCards1 = new Card[handSize()-cardsRequired()]; 
		Hand h = null;
		
		if (allCards.length>=handSize()) {
			int s = 0;
			for(int i = 0; i<subset.length;i++) {
				mainCards1[i]= allCards[subset[i]];
			}
			for ( int j = 0; j< sideCards1.length;j++) {
				if (j!=subset[s]) {
					sideCards1[j]=allCards[j];
					s++;
				}
			}
			
			if (canSatisfy(mainCards1)) {
				h = new Hand(mainCards1,sideCards1,this);
				
				if (name.equals("Full House")) {
					int card1 = mainCards1[0].getRank();
					int card2 = mainCards1[mainCards1.length-1].getRank();
					int card1temp = 0;
					int card2temp = 0;
					
					if (card1!=card2) {
						for (int k = 0;k<mainCards1.length;k++) {
							if(mainCards1[k].getRank()==card1) {
								card1temp++;
							}
							else if(mainCards1[k].getRank()==card2) {
								card2temp++;
							}
						}			
						if (card2temp> card1temp) {
							int l = mainCards1.length-card2temp;					
							for (int d = 0;d<mainCards1.length;d++) {
								if (l+d<mainCards1.length) {
								Card tempC=mainCards1[d];
								mainCards1[d] = mainCards1[l+d]; 
								mainCards1[l+d]=tempC;
								}
								
							}							
							h = new Hand(mainCards1,sideCards1,this);				
						}
					}
				}
			}
		}
		return h;
	}
	
	@Override
	public Hand getBestHand(Card[] allCards) {
		Hand tempH = null;
		if (allCards.length>=handSize()) {
			ArrayList<int[]> subsets = SubsetFinder.findSubsets(allCards.length,cardsRequired());
			ArrayList<Hand> handArrList = new ArrayList<Hand>();

		for (int i = 0; i<subsets.size();i++) {
			int arr[] = subsets.get(i);
			handArrList.add(createHand(allCards,arr));
			}
		
		for (int j = 0; j<handArrList.size();j++) {
			if(handArrList.get(j)==null) {
				handArrList.remove(j);
				j=j-1;
				}
			}
		Hand h1 [] = new Hand[handArrList.size()];
		for (int j = 0; j<handArrList.size();j++) {
			h1[j]=handArrList.get(j);
		}	
		Arrays.sort(h1);
		if (h1.length>0){
			tempH=h1[0];
			}
		}
		return tempH;
	}
}
