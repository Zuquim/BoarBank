package bankOps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Operations {

	public static int counter = 0;
	private static int AN_MAX_LENGTH = 5;

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		boolean quit = false;

		ArrayList<Client> accountArray = new ArrayList<Client>();

		FileReader fr = new FileReader("acc.txt");
		BufferedReader br = new BufferedReader(fr);
		LineNumberReader lnr = new LineNumberReader(fr);

		int j = 0;
		String auxOwner = "";
		int auxNumber = 0;
		double auxBalance = 0.0;
		String line = br.readLine();
		while (line != null) {
			if (j == 0) {
				auxOwner = line;
			} else if (j == 1) {
				if (line.length() <= AN_MAX_LENGTH)
					auxNumber = Integer.parseInt(line);
				else {
					System.out.println("\tERROR!");
					System.out.println("Account number exceds the char limit (5)!");
					line = null;
					break;
				}
			} else if (j == 2) {
				auxBalance = formatCurrency(line);
				accountArray.add(new Client(auxOwner, auxNumber, auxBalance));
				j = -1;
			}
			line = br.readLine();
			j++;
		}
		welcomeBanner();
		do {
			boolean main = false;
			mainMenu();
			int mainMenuOption = input.nextInt();
			switch (mainMenuOption) {
			case 1:
				System.out.println("\n\n" + accountArray.toString() + "\n\n");
				break;
			case 2:
				Collections.sort(accountArray);
				System.out.println("\n   {2}\n   >>>  Accounts reordered  <<<\n");
				break;
			case 3:
				do {
					boolean search = false;
					System.out.println("\n   {3}\t[Account management]");
					System.out.print("\n\tSearch account by its number: ");
					int accNumber = input.nextInt();
					int index = findAccountByNumber(accountArray, accNumber, counter);
					// System.out.println("\n {3}\tArray index: " + index);
					do {
						accountManager(accNumber);
						int accountMenuOption = input.nextInt();
						switch (accountMenuOption) {
						case 1:
							String currentOwner = accountArray.get(index).getAccountOwner();
							System.out.print("\n      {3|1}\tCurrent account owner: " + currentOwner + "\n");
							break;
						case 2:
							double currentBalance = accountArray.get(index).getAccountBalance();
							System.out.print("\n      {3|2}\tCurrent account balance: $ " + currentBalance + "\n");
							break;
						case 3:
							System.out.print("\n      {3|3}\tAmount to be deposited: $ ");
							double depositCurrency = input.nextDouble();
							accountArray.get(index).deposit(depositCurrency);
							break;
						case 4:
							System.out.print("\n      {3|4}\tAmount to be withdrawn: $ ");
							double withdrawCurrency = input.nextDouble();
							accountArray.get(index).withdraw(withdrawCurrency);
							break;
						case 5:
							search = true;
							break;
						case 7:
							main = true;
							break;
						case 9:
							quit = true;
							System.out.println("\n < Quitting >\n");
							break;

						default:
							break;
						}
					} while (!search && !main && !quit);
				} while (!main && !quit);
				break;
			case 9:
				quit = true;
				System.out.println("\n < Quitting >\n");
				break;

			default:
				break;
			}

		} while (!quit);
		input.close();
		lnr.close();
		br.close();
	}

	private static int findAccountByNumber(ArrayList<Client> accountArray, int accNumber, int i) {
		counter = i;
		if (accountArray.get(counter).getAccountNumber() == accNumber) {
			int index = counter;
			counter = 0;
			return index;
		} else if (counter <= accountArray.size()) {
			return findAccountByNumber(accountArray, accNumber, ++counter);
		} else
			return 0;

	}
	
	private static Double formatCurrency(String inputCurrency) {
		String outputCurrency = inputCurrency.replace(".", "");
		outputCurrency = outputCurrency.replaceAll(",", ".");
		return Double.parseDouble(outputCurrency);
	}

	private static void welcomeBanner() {
		System.out.println("\n\t\t\t _________________________________");
		System.out.println("\t\t\t |                               |");
		System.out.println("\t\t\t |   Welcome to the Boar Bank!   |");
		System.out.println("\t\t\t |_______________________________|\n");
		System.out.println("\t       ___");
		System.out.println("\t       ',_`\"\"\\        .---,");
		System.out.println("\t          \\   :-\"\"``/`    |");
		System.out.println("\t           `;'     //`\\   /");
		System.out.println("\t           /   __     |   ('.");
		System.out.println("\t          |_ ./O)\\     \\  `) \\");
		System.out.println("\t         _/-.    `      `\"`  |`-.");
		System.out.println("\t     .-=; `                  /   `-.");
		System.out.println("\t    /o o \\ (\\__,.           .       '.");
		System.out.println("\t    L._._;__\\_/          .            `'-.");
		System.out.println("\t      `'-.`             '                 `'-.");
		System.out.println("\t          `.         '                        `-._");
		System.out.println("\t            '-._. -'                              '.");
		System.out.println("\t               \\                                    `\\");
		System.out.println("\t                |                                     \\");
		System.out.println("\t                |    |                                 ;   _.");
		System.out.println("\t                \\    |           |                     |-.((");
		System.out.println("\t                 ;.  \\           /    /                |-.`\\)");
		System.out.println("\t                 | '. ;         /    |                 |(_) )");
		System.out.println("\t                 |   \\ \\       /`    |                 ;'--'");
		System.out.println("\t                  \\   '.\\    /`      |                /");
		System.out.println("\t                   |   /`|  ;        \\               /");
		System.out.println("\t                   |  |  |  |-._      '.           .'");
		System.out.println("\t                   /  |  |  |__.`'---\"_;'-.     .-'");
		System.out.println("\t                  //__/  /  |    .-'``     _.-'`");
		System.out.println("\t                        //__/   //___.--''`\n");
	}

	private static void mainMenu() {
		System.out.println("\n\t[Main Menu]\n");
		System.out.println("[1]\tPrint all accounts (detailed)");
		System.out.println("[2]\tOrder accounts by currency balance");
		System.out.println("[3]\tAccount management");
		System.out.println("[9]\tQuit");
		System.out.print("\n   > ");
	}

	private static void accountManager(int accNumber) {
		System.out.println("\n   {3}\tManaging account #" + accNumber);
		System.out.println("\t[1]\tDisplay current owner");
		System.out.println("\t[2]\tDisplay current balance");
		System.out.println("\t[3]\tDeposit currency");
		System.out.println("\t[4]\tWithdraw currency");
		System.out.println("\t[5]\tBack to search account");
		System.out.println("\t[7]\tBack to main menu");
		System.out.println("\t[9]\tQuit");
		System.out.print("\n           > ");
	}
}
