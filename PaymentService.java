import java.sql.*;
import java.util.Scanner;

public class PaymentService {

    private Connection con;

    public PaymentService(Connection con) {
        this.con = con;
    }


    public void makePayment(Scanner sc) {

        try {

            System.out.print("\nEnter Reservation ID : ");
            int reservationId = sc.nextInt();

            // Get room details
            String query = "SELECT r.room_no, rm.price, rm.category " +
                           "FROM reservation r " +
                           "JOIN rooms rm ON r.room_no = rm.room_no " +
                           "WHERE r.reservation_id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, reservationId);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Reservation Not Found.");
                return;
            }

            double amount = rs.getDouble("price");

            System.out.println("\n========= PAYMENT =========");
            System.out.println("Room No : " + rs.getInt("room_no"));
            System.out.println("Category : " + rs.getString("category"));
            System.out.println("Amount : ₹" + amount);

            System.out.println("\nSelect Payment Method");
            System.out.println("1. Cash");
            System.out.println("2. Card");
            System.out.println("3. UPI");

            System.out.print("Choice : ");
            int choice = sc.nextInt();

            String method;

            switch(choice){

                case 1:
                    method="Cash";
                    break;

                case 2:
                    method="Card";
                    break;

                case 3:
                    method="UPI";
                    break;

                default:
                    System.out.println("Invalid Choice");
                    return;

            }

            String insert =
                    "INSERT INTO payment(reservation_id,amount,payment_method,payment_status) VALUES(?,?,?,?)";

            PreparedStatement ps2 = con.prepareStatement(insert);

            ps2.setInt(1,reservationId);
            ps2.setDouble(2,amount);
            ps2.setString(3,method);
            ps2.setString(4,"Paid");

            int rows = ps2.executeUpdate();

            if(rows>0){

                System.out.println("\nPayment Successful!");
                System.out.println("Booking Confirmed.");

            }

        }

        catch(Exception e){

            System.out.println(e.getMessage());

        }

    }


    public void viewPayments(){

        try{

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT * FROM payment");

            System.out.println("\n=========== PAYMENT HISTORY ===========");

            while(rs.next()){

                System.out.println("--------------------------------");

                System.out.println("Payment ID : "
                        + rs.getInt("payment_id"));

                System.out.println("Reservation ID : "
                        + rs.getInt("reservation_id"));

                System.out.println("Amount : ₹"
                        + rs.getDouble("amount"));

                System.out.println("Method : "
                        + rs.getString("payment_method"));

                System.out.println("Status : "
                        + rs.getString("payment_status"));

                System.out.println("Date : "
                        + rs.getTimestamp("payment_date"));

            }

        }

        catch(Exception e){

            System.out.println(e.getMessage());

        }

    }

}
