import java.util.Scanner;

public class Payment extends Invoice {
    private double amountPaid;
    private String paymentDate;
    private String paymentMethod;
    private String checkNumber;
    private String checkType;

    public Payment(String invoiceNumber, String purchaseDate, String customerName, double amount, double amountPaid, String paymentDate, String paymentMethod) {
        super(invoiceNumber, purchaseDate, customerName, amount);
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.checkNumber = "";
        this.checkType = "";
    }

    public Payment(String invoiceNumber, String purchaseDate, String customerName, double amount, double amountPaid, String paymentDate, String paymentMethod, String checkNumber, String checkType) {
        super(invoiceNumber, purchaseDate, customerName, amount);
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.checkNumber = checkNumber;
        this.checkType = checkType;
    }

    public void displayPaymentDetails() {
        displayInvoiceDetails();
        System.out.println("Amount Paid: " + amountPaid);
        System.out.println("Payment Date: " + paymentDate);
        System.out.println("Payment Method: " + paymentMethod);
        
        if (paymentMethod.equals("Check")) {
            System.out.println("Check Number: " + checkNumber);
            System.out.println("Check Type: " + checkType);
        }
    }

    public static void processPayments(Invoice invoice) {
        Scanner scanner = new Scanner(System.in);

        

        double remainingBalance = invoice.getAmount(); 
        boolean isFullyPaid = false;

        while (!isFullyPaid) {
            double amountPaid;
            while (true) {
                System.out.println("\nEnter Payment Amount: ");
                amountPaid = scanner.nextDouble();

                if (amountPaid <= 0) {
                    System.out.println("Error: Payment amount must be greater than 0");
                    continue;
                } else if (amountPaid > remainingBalance) {
                    System.out.printf("Error: Payment exceeds remaining balance (%.2f)!\n", remainingBalance);
                    continue;
                }
                break;
            }
            scanner.nextLine(); 
            System.out.println("Enter Payment Date (YYYY-MM-DD): ");
            String paymentDate = scanner.nextLine();

            int paymentChoice;
            String paymentMethod = "";
            while (true) {
                System.out.println("Enter Payment Method (1 for Cash, 2 for Check): ");
                paymentChoice = scanner.nextInt();

                if (paymentChoice == 1) {
                    paymentMethod = "Cash";
                    break;
                } else if (paymentChoice == 2) {
                    paymentMethod = "Check";
                    break;
                } else {
                    System.out.println("Error: Invalid choice! Please enter 1 for Cash or 2 for Check");
                }
            }
            scanner.nextLine(); 

            Payment payment;
            if (paymentMethod.equalsIgnoreCase("Check")) {
                String checkType = "";
                while (true) {
                    System.out.println("Is the check on-date or post-dated? ('O' for On-date or 'P' for Post-dated): ");
                    String checkChoice = scanner.nextLine().toUpperCase();
                    if (checkChoice.equals("O")) {
                        checkType = "On-date";
                        break;
                    } else if (checkChoice.equals("P")) {
                        checkType = "Post-dated";
                        break;
                    } else {
                        System.out.println("Error: Invalid choice! Please enter 'O' for On-date or 'P' for Post-dated");
                    }
                }

                System.out.println("Enter Check Number: ");
                String checkNumber = scanner.nextLine();

                if (paymentMethod.equalsIgnoreCase("Check")) {
                    System.out.println("Enter Check Number: ");
                    checkNumber = scanner.nextLine();
                    System.out.println("Enter Check Type (On-date/Post-dated): ");
                    checkType = scanner.nextLine();
                    payment = new Payment(invoice.getInvoiceNumber(), invoice.getPurchaseDate(), invoice.getCustomerName(), invoice.getAmount(), amountPaid, paymentDate, paymentMethod, checkNumber, checkType);
                } else {
                    payment = new Payment(invoice.getInvoiceNumber(), invoice.getPurchaseDate(), invoice.getCustomerName(), invoice.getAmount(), amountPaid, paymentDate, paymentMethod);
                }

            remainingBalance -= amountPaid;
            System.out.println("\nPayment Recorded Successfully!\n");
            payment.displayPaymentDetails();

            if (remainingBalance == 0) {
                System.out.println("Invoice fully paid. No remaining balance.");
                isFullyPaid = true;
            } else {
                System.out.printf("\nRemaining Balance: %.2f\n", remainingBalance);
                System.out.println("\nDo you want to record another payment? (Y/N): ");
                String continuePayment = scanner.nextLine().toUpperCase();

                if (!continuePayment.equals("Y")) {
                    System.out.printf("Payment process ended. Remaining balance: %.2f\n", remainingBalance);
                    break;
                }
            }
        }
        scanner.close();
    }
}
}




