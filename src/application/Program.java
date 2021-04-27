package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int option = 2;
		// Part1 - Customer registration
		while (option != 0) {
			if (option != 1) {
				System.out.println("====== Main Menu ======");
				System.out.println("[1] Order cadastre;");
				System.out.println("[0] Exit the application;");
				System.out.print("Insert the option: ");
				option = sc.nextInt();
			}
			
			switch (option) {
			case 0:
				System.out.println("\nEXITING...");
				System.out.println("ENCERRED APPLICATION!");
				option = 0;
				sc.close();
			break;
			case 1:
				System.out.println("\nEnter cliente data: ");
				System.out.print("Name: ");
				sc.nextLine();
				String clientName = sc.nextLine();

				System.out.print("Email: ");
				String email = sc.next();

				System.out.print("Birth date (DD/MM/YYYY): ");
				// String date = sc.next();
				// Date birthDate = sdf.parse(date);
				Date birthDate = sdf.parse(sc.next());
				Client client = new Client(clientName, email, birthDate);

				// Part2 - Order
				System.out.println("Enter order data:");
				System.out.print("Status: ");
				OrderStatus status = OrderStatus.valueOf(sc.next());
				Date moment = new Date();
				Order order = new Order(moment, status, client);

				// Part3 - Items
				System.out.print("How many items to this order? ");
				int n = sc.nextInt();

				for (int i = 0; i < n; i++) {
					System.out.println("Enter #" + (i + 1) + " item data: ");

					System.out.print("Product name: ");
					sc.nextLine();
					String productName = sc.nextLine();

					System.out.print("Product price (cents separated per point): R$");
					Double productPrice = sc.nextDouble();

					Product product = new Product(productName, productPrice);

					System.out.print("Quantity: ");
					Integer quantity = sc.nextInt();

					OrderItem items = new OrderItem(quantity, productPrice, product);

					order.addItem(items);
					System.out.println("");
				}
				System.out.println("\nORDER SUMMARY: ");
				System.out.println(order);
				option = 2;
				System.out.println("");
				break;
				default :
					while(option != 0 && option != 1) {
						System.out.println("");
						System.out.println("Invalid information!");
						System.out.println("Insert one value valid!");
						System.out.println("[1] Order cadastre;");
						System.out.println("[0] Exit the application;");
						System.out.print("Insert the option: ");
						option = sc.nextInt();
						System.out.println("");
						if (option == 0) {
							System.out.println("\nEXITING...");
							System.out.println("ENCERRED APPLICATION!");
							sc.close();
						}
					}
				break;
			}
			
		}
	}
}
