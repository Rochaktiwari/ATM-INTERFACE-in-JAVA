//ATM INTERFACE

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Demo 
{
        private static Map<String, String> userCredentials = new HashMap<>();
        static 
        {
                userCredentials.put("rochak", "123");
                userCredentials.put("mohit", "234");
                userCredentials.put("akshara", "567");
                //add more users-
        }
        
        public static class User 
        {
                private String userID;
                private double balance;
                private final StringBuilder transactionHistory;
                Scanner scanner = new Scanner(System.in);
                
                public User(String userID) 
                {
                        this.userID = userID;
                        this.balance = 0.0;
                        this.transactionHistory = new StringBuilder(userID + "'s Transaction History:\n");
                }
                
                public void showTransactionHistory() 
                {
                        System.out.println(transactionHistory.toString());
                }
                
                public void withdraw() 
                {        
                        System.out.print("Enter withdrawal amount: ");
                        double amount = scanner.nextDouble();

                        if (amount > 0 && amount <= balance) 
                        {
                                balance -= amount;
                                String transaction = "Withdrawal: -Rupees " + amount + "\n";
                                transactionHistory.append(transaction);
                                System.out.println("Withdrawal successful. Remaining balance: Rupees " + balance);
                        }
                        else 
                        {
                                System.out.println("Invalid withdrawal amount or insufficient funds.");
                        }
                
                }
                
                public void deposit() 
                {
                        
                        System.out.print("Enter deposit amount: ");
                        double amount = scanner.nextDouble();
                        if (amount > 0) 
                        {
                                balance += amount;
                                String transaction = "Deposit: +Rupees " + amount + "\n";
                                transactionHistory.append(transaction);
                                System.out.println("Deposit successful. Updated balance: Rupees " + balance);
                        } 
                        else 
                        {
                                System.out.println("Invalid deposit amount.");
                        }
                
                }
                public void transfer() 
                {
                        System.out.print("Enter recipient's user ID: ");
                        String recipientID = scanner.next();
                        System.out.print("Enter transfer amount: ");
                        double amount = scanner.nextDouble();
                        if (amount > 0 && amount <= balance) 
                        {
                                balance -= amount;
                                String senderTransaction = "Transfer to " + recipientID + ": -Rupees " + amount + "\n";
                                transactionHistory.append(senderTransaction);
                                User recipient = new User(recipientID);
                                recipient.balance += amount;
                                String recipientTransaction = "Transfer from " + userID + ": +Rupees " + amount + "\n";
                                recipient.transactionHistory.append(recipientTransaction);

                                System.out.println("Transfer successful. Updated balance: Rupees " + balance);
                        } 
                        else 
                        {
                                System.out.println("Invalid transfer amount or insufficient funds.");
                        }
                }        
        }
        public static void main(String[] args) 
        {
                Scanner scanner = new Scanner(System.in);

                User user = authenticateUser(scanner);

                if (user != null) 
                {
                        displayMenu();

                        int choice;
                        do 
                        {
                                System.out.print("Enter your choice: ");
                                choice = scanner.nextInt();
                                switch (choice) 
                                {
                                        case 1:
                                        user.showTransactionHistory();
                                        break;
                                        case 2:
                                                user.withdraw();
                                                break;
                                        case 3:
                                                user.deposit();
                                                break;
                                        case 4:
                                                user.transfer();
                                                break;
                                        case 5:
                                                System.out.println("Thank you for using the ATM. Goodbye!");
                                                break;
                                        default:
                                                System.out.println("Invalid choice. Please enter a valid option.");
                                                break;
                                }
                } while (choice != 5);
                }
                else 
                {
                        System.out.println("Authentication failed. Exiting...");
                }
                scanner.close();
        }

        private static User authenticateUser(Scanner scanner) 
        {
                System.out.print("Enter user ID: ");
                String userID = scanner.next();

                if (userCredentials.containsKey(userID)) 
                {
                        System.out.print("Enter password: ");
                        String password = scanner.next();
                        if (password.equals(userCredentials.get(userID))) 
                        {
                                return new User(userID);
                        }
                }
                return null;
        }
        private static void displayMenu() 
        {
                System.out.println("ATM Menu:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");
        }       
}
