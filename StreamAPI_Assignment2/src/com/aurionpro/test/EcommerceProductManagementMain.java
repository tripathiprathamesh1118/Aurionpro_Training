package com.aurionpro.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.aurionpro.model.EcommerceProduct;

public class EcommerceProductManagementMain {
	public static void main(String[] args) {
		Scanner userinput = new Scanner(System.in);
		List<EcommerceProduct> products = new ArrayList<>();
		Set<Integer> productIds = new HashSet<>();
		Set<String> productNames = new HashSet<>();
		Set<String> validCategories = new HashSet<>(
				Arrays.asList("Electronics", "Furniture", "Spiritual", "Stationary", "Random"));

		int n = 0;
		while (true) {
			try {
				System.out.print("Enter the number of products: ");
				n = Integer.parseInt(userinput.nextLine());
				if (n <= 0)
					throw new NumberFormatException();
				break;
			} catch (NumberFormatException e) {
				System.out.println("Exception: Please enter a valid positive integer.");
			}
		}

		for (int i = 0; i < n; i++) {
			System.out.println("\nEnter details for product " + (i + 1) + ": ");

			int productId;
			while (true) {
				try {
					System.out.print("Product ID: ");
					productId = Integer.parseInt(userinput.nextLine());

					if (productId < 0) {
						System.out.println("Exception: Product ID should be positive");
					} else if (productIds.contains(productId)) {
						System.out.println("Exception: Product ID must be unique.");
					} else {
						productIds.add(productId);
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("Exception: Please enter a valid positive integer.");
				}
			}

			String name;
			while (true) {
				System.out.print("Product Name: ");
				name = userinput.nextLine().trim();

				if (name.isEmpty()) {
					System.out.println("Exception: Product name cannot be empty.");
				} else if (productNames.contains(name)) {
					System.out.println("Exception: Product name must be unique.");
				} else {
					productNames.add(name);
					break;
				}
			}

			String category;
			while (true) {

				System.out.println("Choose a category from the following options:");
				System.out.println("1. Electronics");
				System.out.println("2. Furniture");
				System.out.println("3. Spiritual");
				System.out.println("4. Stationary");
				System.out.println("5. Random");

				String userChoice = userinput.nextLine().trim();

				switch (userChoice) {
				case "1":
					category = "Electronics";
					break;
				case "2":
					category = "Furniture";
					break;
				case "3":
					category = "Spiritual";
					break;
				case "4":
					category = "Stationary";
					break;
				case "5":
					category = "Random";
					break;
				default:
					System.out.println("Exception: Invalid choice. Please enter a number between 1 and 5.");
					continue;
				}

				break;
			}

			double price;
			while (true) {
				try {
					System.out.print("Price (must be positive): ");
					price = Double.parseDouble(userinput.nextLine());

					if (price < 0) {
						System.out.println("Exception: Price cannot be negative.");
					} else {
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("Exception: Please enter a valid price.");
				}
			}

			double rating;
			while (true) {
				try {
					System.out.print("Rating (0 to 5): ");
					rating = Double.parseDouble(userinput.nextLine());

					if (rating < 0 || rating > 5) {
						System.out.println("Exception: Rating must be between 0 and 5.");
					} else {
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("Exception: Please enter a valid rating.");
				}
			}

			products.add(new EcommerceProduct(productId, name, category, price, rating));
		}

		System.out.println("\nProducts priced above 5000 is/are: ");
		products.stream().filter(product -> product.getPrice() > 5000).forEach(System.out::println);

		System.out.println("\nProducts sorted by rating (high to low) are: ");
		products.stream().sorted(Comparator.comparingDouble(EcommerceProduct::getRating).reversed())
				.forEach(System.out::println);

		System.out.println("\nProducts grouped by category are: ");
		Map<String, List<EcommerceProduct>> productsByCategory = products.stream()
				.collect(Collectors.groupingBy(EcommerceProduct::getCategory));
		productsByCategory.forEach((category, productList) -> {
			System.out.println("");
			System.out.println(category + ": ");
			productList.forEach(product -> {

				System.out.println(product);
			});
		});

		System.out.println("\n\nMost expensive product is: ");
		products.stream().max(Comparator.comparingDouble(EcommerceProduct::getPrice)).ifPresent(System.out::println);

		double averagePrice = products.stream().mapToDouble(EcommerceProduct::getPrice).average().orElse(0);
		System.out.println("\nAverage price of all products are: " + averagePrice);

		System.out.println("\nProduct names in uppercase are: ");
		products.stream().map(product -> product.getName().toUpperCase()).forEach(System.out::println);

		System.out.println("\nTotal number of products per category are: ");
		Map<String, Long> productCountByCategory = products.stream()
				.collect(Collectors.groupingBy(EcommerceProduct::getCategory, Collectors.counting()));
		productCountByCategory.forEach((category, count) -> {
			System.out.println(category + ": " + count);
		});

		userinput.close();
	}
}
