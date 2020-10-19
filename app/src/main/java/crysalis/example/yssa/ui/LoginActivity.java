package crysalis.example.yssa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import crysalis.example.yssa.databinding.ActivityLoginBinding;
import pojos.Order;
import pojos.Product;
import pojos.Slot;
import services.YssaConnectionService;

public class LoginActivity extends AppCompatActivity {

    /* TODO:
    -openfire for messenger
    -openLdap for directory
    -mamp/mysql for external database
    -Room/SharedPreferences for internal storage of user credentials
    -all tasks and services will be run in this activity
    -need to write task in seperate intelliJ for bootstrapping order/load completion functions
    -need to write task in seperate intelliJ for bootstrapping retrieving workload from remote server
    -need to write task in seperate intelliJ for bootstrapping sending updates to remote server
    -Tomcat****
    -retrieve orders
    */

    EditText etUsername;
    EditText etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        startService(new Intent(this, YssaConnectionService.class));
        etUsername = binding.etLoginUsername;
        etPassword = binding.etLoginPassword;
        btnLogin = binding.loginButton;
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username =  etUsername.getText().toString();
                String password =  etPassword.getText().toString();
                startActivity(new Intent(getApplicationContext(), ManagementConsoleActivity.class));
            }
        });
    }

    public ArrayList<Order> retrieveOrders(Connection con) {
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM `orders`").executeQuery();
            while (rs.next()) {
                ResultSet rs2 = con.prepareStatement("SELECT * FROM `orderItems` WHERE " +
                        "`OrderId` = " + rs.getInt(1)).executeQuery();
                while(rs2.next()) {
                    Product product = getProduct(rs2.getInt(2));
                    products.add(product);
                }
                Order order = new Order(products, rs.getInt(1), rs.getBoolean(2));
                orders.add(order);
                System.err.println("OrderId: " + order.getOrderNumber());
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading products from MySQL...");
        }

        return orders;
    }

    public ArrayList<Product> retrieveProducts(Connection con) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM `products`").executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getString(6));
                products.add(product);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading products from MySQL...");
        }
        return products;
    }

    public Product getProduct(int productId) {
        Connection con = YssaConnectionService.getInstance().getSqlConnection();
        Product product = null;
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM `products` WHERE `ProductId`=" +
                    productId).executeQuery();
            if (rs.next()) {
                product = new Product(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getString(6));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            System.err.println("Error retrieving product: " + productId + " from MySQL");
        }
        return product;
    }
    public ArrayList<Slot> retrieveSlots(Connection con) {
        ArrayList<Slot> slots = new ArrayList<>();
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM `slots`").executeQuery();
            while (rs.next()) {
                Slot slot = new Slot(rs.getInt(1), rs.getInt(2), getProduct(rs.getInt(3)));
                slots.add(slot);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading slots from MySQL...");
        }
        return slots;
    }
}