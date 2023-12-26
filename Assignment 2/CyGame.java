package hw2;

/**
 * Model of a Monopoly-like game. Two players take turns rolling dice to move
 * around a board. The game ends when one of the players has at least
 * MONEY_TO_WIN money or one of the players goes bankrupt (has negative money).
 * 
 * @author AditiNachnani
 */

public class CyGame {
	/**
	 * Do nothing square type.
	 */
	public static final int DO_NOTHING = 0;
	/**
	 * Pass go square type.
	 */
	public static final int PASS_GO = 1;
	/**
	 * Cyclone square type.
	 */
	public static final int CYCLONE = 2;
	/**
	 * Pay the other player square type.
	 */
	public static final int PAY_PLAYER = 3;
	/**
	 * Get an extra turn square type.
	 */
	public static final int EXTRA_TURN = 4;
	/**
	 * Jump forward square type.
	 */
	public static final int JUMP_FORWARD = 5;
	/**
	 * Stuck square type.
	 */
	public static final int STUCK = 6;
	/**
	 * Points awarded when landing on or passing over go.
	 */
	public static final int PASS_GO_PRIZE = 200;
	/**
	 * The amount payed to the other player per unit when landing on a
	 * PAY_PLAYER square.
	 */
	public static final int PAYMENT_PER_UNIT = 20;
	/**
	 * The amount of money required to win.
	 */
	public static final int MONEY_TO_WIN = 400;
	/**
	 * The cost of one unit.
	 */
	public static final int UNIT_COST = 50;
	/**
	 * Number of squares on the game board. 
	 */
	private int numSquares;
	/**
	 * Keeps track of current player.
	 */
	private int CurrentPlayer = 1; 
	/**
	 * Keeps track of player 1's square. 
	 */
	private int Player1Square = 0;
	/**
	 * Keeps track of player 1's money. 
	 */
	private int Player1Money;
	/**
	 * Keeps track of player 1's units. 
	 */
	private int Player1Units = 1; 
	/**
	 * Keeps track of player 2's square. 
	 */
	private int Player2Square = 0;
	/**
	 * Keeps track of player 2's money. 
	 */
	private int Player2Money;
	/**
	 * Keeps track of player 2's units. 
	 */
	private int Player2Units = 1; 
	
	/**
	 * Constructs a game that has the given number of squares and starts both players on square 0.
	 * @param numSquares
	 * 		Number of squares on board 
	 * @param startingMoney
	 * 		starting money for each player
	 */
	public CyGame (int numSquares, int startingMoney) {
		this.numSquares = numSquares;
		Player1Money = startingMoney;
		Player2Money = startingMoney;
	}
	
	/**
	 * Method called to indicate the current player attempts to buy one unit.
	 */
	public void buyUnit() {
		if (isGameEnded() == false) {
			if (getCurrentPlayer()==1) {
				if (getSquareType(Player1Square) == DO_NOTHING && Player1Money>=UNIT_COST) {
					Player1Units = Player1Units + 1;
					Player1Money = Player1Money - UNIT_COST;
					endTurn();
					}
				}
			else {
				if (getSquareType(Player2Square) == DO_NOTHING && Player2Money>=UNIT_COST) {
					Player2Units = Player2Units + 1;
					Player2Money = Player2Money - UNIT_COST;
					endTurn();
					}
				}
			}
		}
	
	/**
	 * Method called to indicate the current player passes or completes their turn.
	 */
	public void endTurn() {
		if (isGameEnded()==false) {
			if (CurrentPlayer==1) {
				CurrentPlayer = 2;
			}
			else {
				CurrentPlayer = 1;
			}
		}
	}
	
	/**
	 * Get the current player.
	 * @return
	 * 		1 if it is currently Player 1's turn, otherwise 2
	 */
	public int getCurrentPlayer() {
		return CurrentPlayer;
	}
	
	/**
	 * Get Player 1's money.
	 * @return
	 * 		Player 1's money
	 */
	public int getPlayer1Money() {
		return Player1Money;
	}
	
	/**
	 * Get Player 1's square.
	 * @return
	 * 		the square number
	 */
	public int getPlayer1Square() {
		return Player1Square;
	}
	
	/**
	 * Get Player 1's units.
	 * @return
	 * 		Player 1's units.
	 */
	public int 	getPlayer1Units() {
		return Player1Units;
	}
	
	/**
	 * Get Player 2's money.
	 * @return
	 * 		Player 2's money
	 */
	public int getPlayer2Money() {
		return Player2Money;
	}
	
	/**
	 * Get Player 2's square.
	 * @return
	 * 		the square number
	 */
	public int 	getPlayer2Square() {
		return Player2Square;
	}
	
	/**
	 * Get Player 2's units.
	 * @return
	 * 		Player 2's units.
	 */
	public int	getPlayer2Units() {
		return Player2Units;
	}
	
	/**
	 * Get the type of square for the given square number.
	 * @param square
	 * 		the square number
	 * @return
	 * 		the type of square for the given square number.
	 */
	public int getSquareType(int square) {
		int squareType = DO_NOTHING; //stores the square type 
		
		if (square==0) {
			squareType = PASS_GO;
		}
		else if ((numSquares-1)==square){
			squareType = CYCLONE;
		}
		else if (square%5==0){
			squareType = PAY_PLAYER;
		}
		else if ((square%7==0)||(square%11==0)){
			squareType = EXTRA_TURN;
		}
		else if (square%3==0){
			squareType = STUCK;
		}
		else if (square%2==0){
			squareType = JUMP_FORWARD;
		}
		return squareType;
	}
	
	/**
	 * Returns true if game is over, false otherwise.
	 * @return
	 * 		true if the game is over, false otherwise
	 */
	public boolean isGameEnded() {
		if ((Player1Money >= MONEY_TO_WIN)|| (Player2Money >= MONEY_TO_WIN)||(Player2Money <0) || (Player1Money<0)) {
			return true;
		}
		else {
			return false;
		}
	}
	 
	/**
	 * Method called to indicate the dice has been rolled.
	 * @param value
	 * 		the number rolled by the dice (1 to 6 inclusive)
	 */
	public void roll(int value) {
		int temp_squares = 0; // keeps track of the original position of the current player before rolling 
		boolean endTurn = true; //true if turn can end
		int total_squares = 0; //keeps track of the total squares traveled by the current player
		
		if (getCurrentPlayer()==1) {
			temp_squares = getPlayer1Square();
		}
		else {
			temp_squares = getPlayer2Square();
		}
		
		total_squares = temp_squares + value; 
		
		if (((getSquareType(temp_squares)!=STUCK || value%2==0 ) && isGameEnded() == false)) {
			if ((value + temp_squares)<numSquares){
				temp_squares = value + temp_squares;
				}
			else {
				temp_squares = (temp_squares + value) % numSquares;
				}
		}
		
		 if (getSquareType(temp_squares)==CYCLONE){
			if (getCurrentPlayer()==1) {
				Player1Square = Player2Square;
			}
			else {
				Player2Square = Player1Square;
			}
		}
		 
		else if (getSquareType(temp_squares)==PAY_PLAYER){
			if (getCurrentPlayer()==1) {
				Player1Money = Player1Money-(getPlayer2Units()* PAYMENT_PER_UNIT);
				Player2Money = Player2Money+(getPlayer2Units()* PAYMENT_PER_UNIT);
			}
			else {
				Player2Money = Player2Money-(getPlayer1Units()* PAYMENT_PER_UNIT);
				Player1Money = Player1Money+(getPlayer1Units()* PAYMENT_PER_UNIT);
			}
		}
		 
		else if (getSquareType(temp_squares)==EXTRA_TURN){
			endTurn = false; 
		}
		 
		else if (getSquareType(temp_squares)==JUMP_FORWARD && isGameEnded()!=true){
			total_squares = total_squares + 4;
			if ((4 + temp_squares)<numSquares) {
				temp_squares = temp_squares +4;
			}
			else {
				temp_squares = (temp_squares + 4)%numSquares;
			}
		}
		 
		if (getSquareType(temp_squares)==PASS_GO || total_squares>(numSquares-1) ){
			if (getCurrentPlayer()==1) {
				Player1Money = Player1Money + PASS_GO_PRIZE;
			}
			else {
				Player2Money = Player2Money + PASS_GO_PRIZE;
			}
		}
		
		if (getSquareType(temp_squares)!=CYCLONE){
			if (getCurrentPlayer()==1) {
				Player1Square = temp_squares;
			}
			else {
				Player2Square = temp_squares;
			}
		}
		
		if (endTurn == true) {
			endTurn();
			}
		}
	
	/**
	 * Method called to indicate the current player attempts to sell one unit.
	 */
	public void sellUnit() {
		if (isGameEnded() == false) {
			if (getCurrentPlayer()==1) {
				if (Player1Units>0) {
					Player1Units = Player1Units - 1;
					Player1Money = Player1Money + UNIT_COST;
					endTurn();
				}
			}
			
			else {
				if (Player2Units>0) {
					Player2Units = Player2Units - 1;
					Player2Money = Player2Money + UNIT_COST;
					endTurn();
					}
				}
			}
		}
	
	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player 1*: (0, 0, $0) Player 2: (0, 0, $0)</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which players turn it
	 * is. The numbers (0, 0, $0) indicate which square the player is on, how
	 * many units the player has, and how much money the player has
	 * respectively.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player 1%s: (%d, %d, $%d) Player 2%s: (%d, %d, $%d)";
		String player1Turn = "";
		String player2Turn = "";
		if (getCurrentPlayer() == 1) {
			player1Turn = "*";
			} 
		else {
			player2Turn = "*";
			}
		return String.format(fmt, player1Turn, getPlayer1Square(), getPlayer1Units(), getPlayer1Money(),player2Turn, getPlayer2Square(), getPlayer2Units(), getPlayer2Money());
	}
}
