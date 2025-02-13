import java.util.Scanner;

class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String correctPin = "2004";
        double balance = 80000.00;
        
        System.out.print("Enter your PIN: ");
        String userPin = scanner.nextLine();
        
        try {
            if (!userPin.equals(correctPin)) {
                throw new InvalidPinException("Invalid PIN entered.");
            }
            
            System.out.print("Enter withdrawal amount: ");
            double withdrawalAmount = scanner.nextDouble();
            
            if (withdrawalAmount > balance) {
                throw new InsufficientBalanceException("Insufficient balance.");
            }
            
            balance -= withdrawalAmount;
            System.out.println("Withdrawal successful.");
            
        } catch (InvalidPinException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Remaining Balance: $" + balance);
        }

        scanner.close();
    }
}
