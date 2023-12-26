package hw1;
/**
 * Model of a printer.
 * @author AditiNachnani
 */
public class Printer 
{
	/**
	 * Maximum capacity of the tray of the number of paper sheets the printer can hold.
	 */
	private int tray_cap = 0;
	
	/**
	 * Stores the copies of a document that is a specified page length.
	 */
	private int documentSheets;
	
	/**
	 * Number of sheets available in the tray.
	 */
	private int num_sheets_tray;
	
	/**
	 * Number of sheets available in the printer.
	 */
	private int num_sheets = 0; 
	
	/**
	 * Next page number of the document that will be printed. 
	 */
	private int next_page = 0;
	
	/**
	 * Count of all pages printed by the printer since its construction.  
	 */
	private int total_page;

	/**
	 * Constructs a new printer with the given maximum tray capacity of the number
	 * of paper sheets it can hold.
	 * @param trayCapacity
	 *   the maximum tray capacity for this Printer
	 */
	public Printer(int trayCapacity)
	{
		tray_cap = trayCapacity;
	}

	/**
	 * Starts a new print job to make copies of a document that is a specified page length.
	 * @param documentPages
	 *   makes copies of a document that is a specified page length
	 */
	public void startPrintJob(int documentPages) 
	{
		documentSheets = documentPages;
		next_page = 0;
	}

	/**
	 * Returns the number of sheets available for printing.
	 * @return 
	 *   number of sheets available in the printer
	 */
	public int getSheetsAvailable() 
	{
		return num_sheets;
	}

	/**
	 * Returns the next page number of the document that will be printed.
	 * @return
	 *    next page number of the document that will be printed
	 */
	public int getNextPage() 
	{
		return next_page;
	}

	/**
	 * Returns the count of all pages printed by the printer since its construction.
	 * @return
	 *    count of all pages printed by the printer
	 */
	public int getTotalPages()
	{
		return total_page;
	}

	/**
	 * Increments the total page count, advances the next page to print, and updates number of pages available to the printer.
	 */
	public void printPage() 
	{
		int temp_num_sheets = num_sheets; //stores number of sheets available to to a local variable
		num_sheets = Math.max(0, num_sheets - 1);
		int x = Math.max(0, temp_num_sheets - num_sheets); //stores 0 or 1 to variable x
		total_page = total_page + x;
		next_page = next_page + x;
		num_sheets_tray = num_sheets_tray - x;
		next_page = next_page % documentSheets;
	}

	/**
	 * Removes the paper tray from the printer and makes the sheets available
	 * to the printer zero.
	 */
	public void removeTray()
	{
		num_sheets = 0;
	}

	/**
	 * Makes the sheets available to the printer the same as the number of sheets in the tray.
	 */
	public void replaceTray() 
	{
		num_sheets = num_sheets_tray;
	}

	/**
	 * Removes the tray, adds sheets up to the maximum capacity, and replaces the tray in the printer.
	 * @param sheets
	 *   number of sheets getting added to the tray
	 */
	public void addPaper(int sheets) 
	{
		num_sheets_tray = num_sheets = Math.min(tray_cap, num_sheets_tray + sheets);
		replaceTray();
	}

	/**
	 * Removes the tray, removes sheets, and replaces the tray in the printer.
	 * @param sheets
	 *    number of sheets getting removed from the tray
	 */
	public void removePaper(int sheets) 
	{
		num_sheets_tray = num_sheets = Math.max(0, num_sheets_tray - sheets);
		replaceTray();
	}
}
