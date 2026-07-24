import java.sql.*;
import java.util.Scanner;

public class ReservationService {

    private Connection con;
    private RoomService roomService;

    public ReservationService(Connection con) {
        this.con = con;
        this.roomService = new RoomService(con);
    }
    public void bookRoom(Scanner sc) {

        try {

            System.out.println("\n===== ROOM CATEGORY =====");
            System.out.println("1. Standard");
            System.out.println("2. Deluxe");
            System.out.println("3. Suite");
            System.out.print("Choose Category : ");

            int choice = sc.nextInt();
            sc.nextLine();

            String category = "";

            switch(choice){

                case 1:
                    category="Standard";
                    break;

                case 2:
                    category="Deluxe";
                    break;

                case 3:
                    category="Suite";
                    break;

                default:
                    System.out.println("Invalid Choice");
                    return;

            }

            roomService.searchRoomByCategory(category);

            System.out.print("\nEnter Room Number : ");
            int roomNo = sc.nextInt();
            sc.nextLine();

            if(!roomService.isRoomAvailable(roomNo)){
                System.out.println("Room Already Booked.");
                return;
            }

            System.out.print("Guest Name : ");
            String guest=sc.nextLine();

            System.out.print("Contact Number : ");
            String contact=sc.nextLine();

            String sql="INSERT INTO reservation(guest_name,contact_no,room_no) VALUES(?,?,?)";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,guest);
            ps.setString(2,contact);
            ps.setInt(3,roomNo);

            int rows=ps.executeUpdate();

            if(rows>0){

                roomService.updateRoomStatus(roomNo,"Booked");

                System.out.println("\nReservation Successful!");

            }

        }

        catch(Exception e){

            System.out.println(e.getMessage());

        }

    }


    public void viewReservations(){

        try{

            String sql=
                    "SELECT * FROM reservation";

            Statement st=con.createStatement();

            ResultSet rs=st.executeQuery(sql);

            System.out.println("\n==============================");

            while(rs.next()){

                System.out.println("Reservation ID : "+rs.getInt("reservation_id"));

                System.out.println("Guest : "+rs.getString("guest_name"));

                System.out.println("Contact : "+rs.getString("contact_no"));

                System.out.println("Room : "+rs.getInt("room_no"));

                System.out.println("Date : "+rs.getTimestamp("reservation_date"));

                System.out.println("-----------------------------");

            }

        }

        catch(Exception e){

            System.out.println(e.getMessage());

        }

    }

    public void bookingDetails(Scanner sc){

        try{

            System.out.print("Enter Reservation ID : ");

            int id=sc.nextInt();

            String sql="SELECT * FROM reservation WHERE reservation_id=?";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setInt(1,id);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){

                System.out.println("\n===== BOOKING DETAILS =====");

                System.out.println("Reservation ID : "+rs.getInt("reservation_id"));

                System.out.println("Guest Name : "+rs.getString("guest_name"));

                System.out.println("Contact : "+rs.getString("contact_no"));

                System.out.println("Room : "+rs.getInt("room_no"));

                System.out.println("Reservation Date : "+rs.getTimestamp("reservation_date"));

            }

            else{

                System.out.println("Reservation Not Found.");

            }

        }

        catch(Exception e){

            System.out.println(e.getMessage());

        }

    }

    
    public void updateReservation(Scanner sc){

        try{

            System.out.print("Reservation ID : ");

            int id=sc.nextInt();

            sc.nextLine();

            System.out.print("New Guest Name : ");

            String guest=sc.nextLine();

            System.out.print("New Contact : ");

            String contact=sc.nextLine();

            String sql=
                    "UPDATE reservation SET guest_name=?,contact_no=? WHERE reservation_id=?";

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,guest);

            ps.setString(2,contact);

            ps.setInt(3,id);

            int rows=ps.executeUpdate();

            if(rows>0){

                System.out.println("Reservation Updated.");

            }

            else{

                System.out.println("Reservation Not Found.");

            }

        }

        catch(Exception e){

            System.out.println(e.getMessage());

        }

    }


    public void cancelReservation(Scanner sc){

        try{

            System.out.print("Reservation ID : ");

            int id=sc.nextInt();

            int roomNo=0;

            String find=
                    "SELECT room_no FROM reservation WHERE reservation_id=?";

            PreparedStatement p1=con.prepareStatement(find);

            p1.setInt(1,id);

            ResultSet rs=p1.executeQuery();

            if(rs.next()){

                roomNo=rs.getInt("room_no");

            }

            else{

                System.out.println("Reservation Not Found.");

                return;

            }

            String delete=
                    "DELETE FROM reservation WHERE reservation_id=?";

            PreparedStatement p2=con.prepareStatement(delete);

            p2.setInt(1,id);

            int rows=p2.executeUpdate();

            if(rows>0){

                roomService.updateRoomStatus(roomNo,"Available");

                System.out.println("Reservation Cancelled Successfully.");

            }

        }

        catch(Exception e){

            System.out.println(e.getMessage());

        }

    }

}
