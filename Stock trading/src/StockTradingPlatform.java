import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Stock {
    String name;
    double price;
    
    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Portfolio {
    private Map<String, Integer> holdings = new HashMap<>();
    private double balance;
    
    Portfolio(double balance) {
        this.balance = balance;
    }
    
    void buyStock(String stock, int quantity, double price) {
        double cost = quantity * price;
        if (cost > balance) {
            System.out.println("Insufficient funds to buy " + stock);
        } else {
            balance -= cost;
            holdings.put(stock, holdings.getOrDefault(stock, 0) + quantity);
            System.out.println("Bought " + quantity + " shares of " + stock);
        }
    }
    
    void sellStock(String stock, int quantity, double price) {
        if (!holdings.containsKey(stock) || holdings.get(stock) < quantity) {
            System.out.println("Not enough shares to sell");
        } else {
            balance += quantity * price;
            holdings.put(stock, holdings.get(stock) - quantity);
            if (holdings.get(stock) == 0) {
                holdings.remove(stock);
            }
            System.out.println("Sold " + quantity + " shares of " + stock);
        }
    }
    
    void displayPortfolio() {
        System.out.println("Portfolio Holdings:");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " shares");
        }
        System.out.println("Balance: $" + balance);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Portfolio portfolio = new Portfolio(10000.0);
        Map<String, Stock> market = new HashMap<>();
        
        market.put("AAPL", new Stock("AAPL", 150.0));
        market.put("GOOGL", new Stock("GOOGL", 2800.0));
        market.put("TSLA", new Stock("TSLA", 750.0));
        
        while (true) {
            System.out.println("1. Buy Stock\n2. Sell Stock\n3. View Portfolio\n4. Exit");
            int choice = scanner.nextInt();
            
            if (choice == 4) break;
            
            switch (choice) {
                case 1:
                    System.out.print("Enter stock symbol: ");
                    String buyStock = scanner.next();
                    if (market.containsKey(buyStock)) {
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        portfolio.buyStock(buyStock, quantity, market.get(buyStock).price);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter stock symbol: ");
                    String sellStock = scanner.next();
                    if (market.containsKey(sellStock)) {
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        portfolio.sellStock(sellStock, quantity, market.get(sellStock).price);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 3:
                    portfolio.displayPortfolio();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}
