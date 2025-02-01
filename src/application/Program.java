package application;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        try (Scanner sc = new Scanner(System.in)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            List<Product> products = new ArrayList<>();

            System.out.print("Enter the number of products: ");
            int n = sc.nextInt();
            sc.nextLine();
            System.out.println();

            for (int i = 0; i < n; i++) {
                System.out.printf("Product #%d data: %n", i + 1);
                System.out.print("Common, used or imported (c/u/i)? ");
                char option = sc.nextLine().charAt(0);

                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Price: ");
                double price = sc.nextDouble();
                sc.nextLine();

                switch (option) {
                    case 'c':
                        products.add(new Product(name, price));
                        break;
                    case 'i':
                        System.out.print("Customs fee: ");
                        double custonsFee = sc.nextDouble();
                        sc.nextLine();
                        products.add(new ImportedProduct(name, price, custonsFee));
                        break;
                    case 'u':
                        System.out.print("Manufacture date (DD/MM/YYYY): ");
                        String dateInput = sc.nextLine();
                        LocalDate date = LocalDate.parse(dateInput, formatter);
                        products.add(new UsedProduct(name, price, date));
                        break;
                    default:
                        System.out.println("Opcao invalida");

                }
                System.out.println();
            }
            System.out.println("PRICE TAGS: ");
            for (Product product : products) {
                System.out.println(product.priceTag());
            }
        }
    }
}
