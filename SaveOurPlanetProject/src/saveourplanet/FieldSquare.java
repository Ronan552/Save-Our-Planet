package saveourplanet;

public class FieldSquare extends Square implements IFieldSquares {

	private Fields field;
	private int minorDevCount;
	private String ownerName;
	private double buyPrice;
	private double minorDevCost;
	private double majorDevCost;
	private double baseRent;
	private double rent;
	private String minorDevName;
	private String majorDevName;
	private int majorDevCount;

	/**
	 * default constructor
	 */
	public FieldSquare() {
		this.ownerName = "For Sale";
		this.minorDevCount = 0;
		this.majorDevCount = 0;
	}

	/**
	 * @param name
	 * @param squareId
	 * @param field
	 * @param buyPrice
	 * @param minorDevCost
	 * @param majorDevCost
	 * @param rent
	 * @param minorDevName
	 * @param majorDevName
	 */
	
	public FieldSquare(String name, int squareId, Fields field, double buyPrice, double minorDevCost,
			double majorDevCost, double baseRent, String minorDevName, String majorDevName) {
		super(name, squareId);
		this.field = field;
		this.buyPrice = buyPrice;
		this.minorDevCost = minorDevCost;
		this.majorDevCost = majorDevCost;
		this.baseRent = baseRent;
		this.minorDevName = minorDevName;
		this.majorDevName = majorDevName;
		this.ownerName = "For Sale";
		this.minorDevCount = 0;
		this.majorDevCount = 0;
		this.rent = this.baseRent;
	}

	/**
	 * @return the majorDevCount
	 */
	public int getMajorDevCount() {
		return majorDevCount;
	}

	/**
	 * @param majorDevCount the majorDevCount to set
	 */
	public void setMajorDevCount(int majorDevCount) {
		this.majorDevCount = majorDevCount;
	}

	/**
	 * @return the baseRent
	 */
	public double getBaseRent() {
		return baseRent;
	}

	/**
	 * @param baseRent the baseRent to set
	 */
	public void setBaseRent(double baseRent) {
		this.baseRent = baseRent;
	}

	/**
	 * @return the minorDevCount
	 */
	public int getMinorDevCount() {
		return minorDevCount;
	}

	/**
	 * @param minorDevCount the minorDevCount to set
	 */
	public void setMinorDevCount(int minorDevCount) {
		this.minorDevCount = minorDevCount;
	}

	/**
	 * @return the majorDevName
	 */
	public String getMajorDevName() {
		return majorDevName;
	}

	/**
	 * @param majorDevName the majorDevName to set
	 */
	public void setMajorDevName(String majorDevName) {
		this.majorDevName = majorDevName;
	}

	/**
	 * @return the rent
	 */
	public double getRent() {
		return rent;
	}

	/**
	 * @param rent the rent to set
	 */
	public void setRent(double rent) {
		this.rent = rent;
	}

	/**
	 * @return the minorDevName
	 */
	public String getMinorDevName() {
		return minorDevName;
	}

	/**
	 * @param minorDevName the minorDevName to set
	 */
	public void setMinorDevName(String minorDevName) {
		this.minorDevName = minorDevName;
	}

	/**
	 * @return the field
	 */
	public Fields getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(Fields field) {
		this.field = field;
	}

	/**
	 * @return the ownerId
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * @return the buyPrice
	 */
	public double getBuyPrice() {
		return buyPrice;
	}

	/**
	 * @param buyPrice the buyPrice to set
	 */
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	/**
	 * @return the minorDevCost
	 */
	public double getMinorDevCost() {
		return minorDevCost;
	}

	/**
	 * @param minorDevCost the minorDevCost to set
	 */
	public void setMinorDevCost(double minorDevCost) {
		this.minorDevCost = minorDevCost;
	}

	/**
	 * @return the majorDevCost
	 */
	public double getMajorDevCost() {
		return majorDevCost;
	}

	/**
	 * @param majorDevCost the majorDevCost to set
	 */
	public void setMajorDevCost(double majorDevCost) {
		this.majorDevCost = majorDevCost;
	}

	@Override
	public void minorDev() {

		this.minorDevCount++;


		switch (this.minorDevCount) {

		case 1:
			this.rent = this.baseRent * 2;
			break;
		case 2:
			this.rent = this.baseRent * 3;
			break;
		case 3:
			this.rent = this.baseRent * 4;
			break;

		}

	}

	@Override
	public void majorDev() {
		
		this.majorDevCount++;
		this.rent = this.rent * 5;

	}

	@Override
	public void displayAll() {
		super.displayAll();
		System.out.printf("%-15s: %s\n", "Field", this.field);
		System.out.printf("%-15s: %.0f\n", "Buy Price", this.buyPrice);
		System.out.printf("%-28s: %.0f\n", "Base Contribution commanded", this.baseRent);
		System.out.printf("[%-25s: %-30s | %s: %-4.0f | %-20s]\n", "Minor Development Opportunity",
				this.minorDevName, "Cost", this.minorDevCost, "Contribution increases 100% with each development. A max of 3 minor developments are allowed");
		System.out.printf("[%-25s: %-30s | %s: %-4.0f | %-20s]\n", "Major Development Opportunity",
				this.majorDevName, "Cost", this.majorDevCost, "Potential future rent of five times that of the rent with zero developments. Must have 3 minor developments prior to major development");

	}

}
