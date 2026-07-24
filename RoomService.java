import java.sql.*;

public class RoomService {

    private Connection con;

    public RoomService(Connection con) {
        this.con = con;
    }


    public void viewAllRooms() {

        String sql = "SELECT * FROM rooms ORDER BY room_no";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n==================== ALL ROOMS ====================");

            System.out.printf("%-10s %-15s %-10s %-15s%n",
                    "Room No", "Category", "Price", "Status");

            System.out.println("----------------------------------------------------------");

            while (rs.next()) {

                System.out.printf("%-10d %-15s %-10.2f %-15s%n",
                        rs.getInt("room_no"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getString("status"));
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }


    public void searchRoomByCategory(String category) {

        String sql = "SELECT * FROM rooms WHERE category=? AND status='Available'";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, category);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n========== AVAILABLE " + category.toUpperCase() + " ROOMS ==========");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("-------------------------------------");

                System.out.println("Room No : " + rs.getInt("room_no"));

                System.out.println("Category : " + rs.getString("category"));

                System.out.println("Price : ₹" + rs.getDouble("price"));

                System.out.println("Status : " + rs.getString("status"));

            }

            if (!found) {

                System.out.println("No Rooms Available.");

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    public boolean isRoomAvailable(int roomNo) {

        String sql = "SELECT status FROM rooms WHERE room_no=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomNo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getString("status").equalsIgnoreCase("Available");

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return false;

    }

    
    public void updateRoomStatus(int roomNo, String status) {

        String sql = "UPDATE rooms SET status=? WHERE room_no=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);

            ps.setInt(2, roomNo);

            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    public double getRoomPrice(int roomNo) {

        String sql = "SELECT price FROM rooms WHERE room_no=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomNo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getDouble("price");

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return 0;

    }


    public String getRoomCategory(int roomNo) {

        String sql = "SELECT category FROM rooms WHERE room_no=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomNo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getString("category");

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return "";

    }

}