package piggyBank;

public class Client implements Comparable<Client> {
	private String accountOwner;
	private int accountNumber;
	private double accountBalance;

	public Client(String accountOwner, int accountNumber, double accountBalance) {
		this.accountOwner = accountOwner;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "Client {\n\taccountOwner = " + accountOwner + "\n\taccountNumber = " + accountNumber
				+ "\n\taccountBalance = " + accountBalance + "\n\t}\n";
	}

	public String getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Double deposit(double depositValue) {
		accountBalance += depositValue;
		return accountBalance;
	}

	public Double withdraw(double withdrawValue) {
		accountBalance -= withdrawValue;
		return accountBalance;
	}

	public int compareTo(Client ab) {
		double cb = ((Client) ab).getAccountBalance();
		return (int) this.accountBalance - (int) cb;
	}

}
