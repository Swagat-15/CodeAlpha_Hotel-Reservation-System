import java.sql.Connection;

public class TestRoomService {

    public static void main(String[] args) {

        Connection con = DBConnection.getConnection();

        if (con == null) {
            System.out.println("Database Connection Failed!");
            return;
        }

        RoomService roomService = new RoomService(con);

        roomService.viewAllRooms();

        System.out.println();

        roomService.searchRoomByCategory("Deluxe");
    }
}