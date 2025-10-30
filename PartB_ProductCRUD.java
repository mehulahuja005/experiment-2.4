import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PartB_ProductCRUD {
    static final String URL = "jdbc:mysql://localhost:3306/testdb";
    static final String USER = "root";
    static final String PASS = "25MAY2004s@";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Scanner sc = new Scanner(System.in)) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            int choice;

            do {
                System.out.println("\n=== PRODUCT CRUD MENU ===");
                System.out.println("1. Add Product");
                System.out.println("2. View All Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> addProduct(con, sc);
                    case 2 -> viewProducts(con);
                    case 3 -> updateProduct(con, sc);
                    case 4 -> deleteProduct(con, sc);
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid Choice!");
                }
            } while (choice != 5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Product Name: ");
        String name = sc.next();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        String sql = "INSERT INTO product (name, price) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setDouble(2, price);
        ps.executeUpdate();
        System.out.println("✅ Product added successfully!");
    }

    static void viewProducts(Connection con) throws SQLException {
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM product");
        System.out.println("ID | Name | Price");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getDouble("price"));
        }
    }

    static void updateProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Product ID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter New Price: ");
        double price = sc.nextDouble();

        String sql = "UPDATE product SET price=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, price);
        ps.setInt(2, id);
        ps.executeUpdate();
        System.out.println("✅ Product updated!");
    }

    static void deleteProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Product ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM product WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("🗑️ Product deleted!");
    }
}
