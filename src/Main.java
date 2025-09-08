import java.util.ArrayList;

public class Order {
    private String customerName;
    private ArrayList<String> items;
    private ArrayList<Double> prices;
    private static int totalOrders = 0;

    public Order(String customerName) {
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.prices = new ArrayList<>();
        totalOrders++;
    }

    public void addItem(String item, double price) throws IllegalArgumentException {
        if (price <= 0) {
            throw new IllegalArgumentException("Invalid price: must be greater than 0");
        }
        if (item == null || item.isEmpty()) {
            throw new IllegalArgumentException("Invalid item: cannot be empty");
        }
        items.add(item);
        prices.add(price);
    }

    public double getTotalAmount() {
        double total = 0.0;
        for (double price : prices) {
            total += price;
        }
        return total;
    }

    public String getOrderSize() {
        int itemCount = getItemCount();
        if (itemCount == 0) {
            return "Empty order";
        } else if (itemCount <= 3) {
            return "Small";
        } else if (itemCount <= 6) {
            return "Medium";
        } else {
            return "Large";
        }
    }

    public void addMultipleItems(String[] items, double... prices) throws IllegalArgumentException {
        if (items.length != prices.length) {
            throw new IllegalArgumentException("Items and prices count mismatch");
        }
        for (int i = 0; i < items.length; i++) {
            try {
                addItem(items[i], prices[i]);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getTotalOrders() {
        return totalOrders;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getItemCount() {
        return items.size();
    }

    public String displayOrder() {
        return "Order for " + customerName + ": " + getItemCount() + " items, Total: $" + getTotalAmount() + ", Size: " + getOrderSize();
    }
}

public class Main {
    public static void main(String[] args) {
        Order order1 = new Order("Alice Johnson");
        Order order2 = new Order("Bob Smith");
        Order order3 = new Order("Charlie Brown");

        order1.addItem("Pizza", 12.99);
        order2.addMultipleItems(new String[]{"Burger", "Fries"}, 8.50, 3.25);
        order3.addItem("Salad", 9.99);

        try {
            order1.addItem("Drink", -5.00);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            order2.addItem("", 4.99);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            order3.addMultipleItems(new String[]{"Burger", "Fries", "Soda"}, 8.50, 3.25);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(order1.displayOrder());
        System.out.println(order2.displayOrder());
        System.out.println(order3.displayOrder());
        System.out.println("Total orders created: " + Order.getTotalOrders());

        Order largestOrder = null;
        double largestTotal = 0.0;
        for (Order order : new Order[]{order1, order2, order3}) {
            double total = order.getTotalAmount();
            if (total > largestTotal) {
                largestOrder = order;
                largestTotal = total;
            }
        }
        System.out.println("Largest order: " + largestOrder.getCustomerName() + " ($" + largestTotal + ")");
    }
}
