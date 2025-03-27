import java.util.Scanner;

public class AccountsRecivableMultiplePayment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Invoice Number: ");
        String invoiceNumber = scanner.nextLine();

        System.out.println("Enter Purchase Date(YYYY-MM-DD): ");
        String purchaseDate = scanner.nextLine();

        System.out.println("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.println("Enter Invoice Amount: ");
        double amount = scanner.nextDouble();
    
        System.out.println("Invoice Created Successfully!\n");
        Invoice invoice = new Invoice(invoiceNumber, purchaseDate, customerName, amount);
        invoice.displayInvoiceDetails();
        System.out.println("\n------------------------------------");

        Payment.processPayments(invoice);


        scanner.close();
    }
}