import java.util.Arrays;
import java.util.Scanner;

public class SmartBanking {

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    final String clear = "\033[H\033[2J";
    final String bold = "\033[;1m";
    final String reset = "\033[0m";
    final String blue = "\033[034m";
    final String red_bold = "\033[31;1m";

    final String DASHBOARD = "💰" + " Welcome to Smart Banking App";
    final String OPEN_NEW_ACCOUNT = "💸" + " Open New Account";
    final String DEPOSITS = "Deposit Money";
    final String WITHDRAW = "Withdraw Money";
    final String TRANSFER = "Transfer Money";
    final String CHECK_BALANCE = "Check Account Balance";
    final String DELETE = "Drop Existing Account";
    final String EXIT = "Exit";

    String screen = DASHBOARD;

    String[][] accountDetails = new String[0][3];

    do {
      final String APP_TITLE = String.format(
        "%s%s%s%s",
        bold,
        blue,
        screen,
        reset
      );
      System.out.println(clear);
      System.out.println("-".repeat(50));
      System.out.println(
        " ".repeat((50 - APP_TITLE.length() + 7) / 2).concat(APP_TITLE)
      );

      System.out.println("-".repeat(50));

      switch (screen) {
        case DASHBOARD:
          System.out.println("\n[1]. Open New Account");
          System.out.println("[2]. Deposit Money");
          System.out.println("[3]. Withdraw Money");
          System.out.println("[4]. Transfer Money");
          System.out.println("[5]. Check Account Balance");
          System.out.println("[6]. Drop Existing Account");
          System.out.println("[7]. Exit\n");
          System.out.print("Enter an option to continue > ");
          int option = scanner.nextInt();
          scanner.nextLine();

          switch (option) {
            case 1:
              screen = OPEN_NEW_ACCOUNT;
              break;
            case 2:
              screen = DEPOSITS;
              break;
            case 3:
              screen = WITHDRAW;
              break;
            case 4:
              screen = TRANSFER;
              break;
            case 5:
              screen = CHECK_BALANCE;
              break;
            case 6:
              screen = DELETE;
              break;
            case 7:
              screen = EXIT;
              break;
            default:
              continue;
          }

          break;
        case OPEN_NEW_ACCOUNT:
          String name;
          int deposit;
          System.out.printf(
            "\nNew Account Holder's ID: SDB-%05d \n",
            (accountDetails.length + 1)
          );

          String id = String.format("SDB-%05d", (accountDetails.length + 1));

          boolean valid;

          do {
            valid = true;
            System.out.print("Enter Account Holder's Name: ");
            name = scanner.nextLine().strip();
            if (name.isBlank()) {
              System.out.printf("%sName can't be empty%s\n", red_bold, reset);
              valid = false;
              continue;
            }
            for (int i = 0; i < name.length(); i++) {
              if (
                !(
                  Character.isLetter(name.charAt(i)) ||
                  Character.isSpaceChar(name.charAt(i))
                )
              ) {
                System.out.printf("%sInvalid Name%s\n", red_bold, reset);
                valid = false;
                break;
              }
            }
          } while (!valid);

          do {
            valid = true;
            System.out.print("Enter Initial Deposit Value: ");
            deposit = scanner.nextInt();
            scanner.nextLine();
            if (!(deposit > 5000)) {
              System.out.printf("%sInsufficient Amount%s\n", red_bold, reset);
              valid = false;
              continue;
            }
          } while (!valid);

          String[][] tempDetails = new String[accountDetails.length + 1][3];

          for (int i = 0; i < accountDetails.length; i++) {
            tempDetails[i] = accountDetails[i];
          }

          tempDetails[tempDetails.length - 1][0] = id;
          tempDetails[tempDetails.length - 1][1] = name;
          tempDetails[tempDetails.length - 1][2] = deposit + "";

          accountDetails = tempDetails;

          System.out.println();
          System.out.printf(
            "%s : %s added sucessfully.\nDo you want to add new customer (Y/n)? ",
            id,
            name
          );
          if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
          screen = DASHBOARD;
          break;
        case DEPOSITS:
        default:
          System.exit(0);
      }
    } while (true);
  }
}
