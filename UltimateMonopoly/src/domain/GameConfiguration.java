package domain;

public class GameConfiguration {
	private int startingMoney;
	private int reachingGoSquareMoney;
	private int totalNumberOfHouse;
	private int totalNumberOfHotel;
	private boolean communityChest;
	private boolean luckCards;
	private boolean goToJail;
	private boolean payTax;
	private boolean auction;
	private boolean mortgage;
	private boolean trade;
	private boolean doubleTheRent;
	
	public GameConfiguration(int startingMoney, int reachingGoSquareMoney, int totalNumberOfHouse,
			int totalNumberOfHotel, boolean communityChest, boolean luckCards, boolean goToJail, boolean payTax,
			boolean auction, boolean mortgage, boolean trade, boolean doubleTheRent) {
		super();
		this.startingMoney = startingMoney;
		this.reachingGoSquareMoney = reachingGoSquareMoney;
		this.totalNumberOfHouse = totalNumberOfHouse;
		this.totalNumberOfHotel = totalNumberOfHotel;
		this.communityChest = communityChest;
		this.luckCards = luckCards;
		this.goToJail = goToJail;
		this.payTax = payTax;
		this.auction = auction;
		this.mortgage = mortgage;
		this.trade = trade;
		this.doubleTheRent = doubleTheRent;
	}
	
	public void changeGameSetup() {
		
	}

	public int getStartingMoney() {
		return startingMoney;
	}

	public int getReachingGoSquareMoney() {
		return reachingGoSquareMoney;
	}

	public int getTotalNumberOfHouse() {
		return totalNumberOfHouse;
	}

	public int getTotalNumberOfHotel() {
		return totalNumberOfHotel;
	}

	public boolean isCommunityChest() {
		return communityChest;
	}

	public boolean isLuckCards() {
		return luckCards;
	}

	public boolean isGoToJail() {
		return goToJail;
	}

	public boolean isPayTax() {
		return payTax;
	}

	public boolean isAuction() {
		return auction;
	}

	public boolean isMortgage() {
		return mortgage;
	}

	public boolean isTrade() {
		return trade;
	}

	public boolean isDoubleTheRent() {
		return doubleTheRent;
	}

	@Override
	public String toString() {
		return "GameConfiguration [startingMoney=" + startingMoney + ", reachingGoSquareMoney=" + reachingGoSquareMoney
				+ ", totalNumberOfHouse=" + totalNumberOfHouse + ", totalNumberOfHotel=" + totalNumberOfHotel
				+ ", communityChest=" + communityChest + ", luckCards=" + luckCards + ", goToJail=" + goToJail
				+ ", payTax=" + payTax + ", auction=" + auction + ", mortgage=" + mortgage + ", trade=" + trade
				+ ", doubleTheRent=" + doubleTheRent + "]";
	}
}