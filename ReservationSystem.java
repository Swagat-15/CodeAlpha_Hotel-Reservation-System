import java.sql.Connection;
import java.util.Scanner;

public class ReservationSystem {

    public static void main(String[] args) {

        Connection con = DBConnection.getConnection();

        if (con == null) {
            System.out.println("Database Connection Failed!");
            return;
        }

        Scanner sc = new Scanner(System.in);

        RoomService roomService = new RoomService(con);
        ReservationService reservationService = new ReservationService(con);
        PaymentService paymentService = new PaymentService(con);

        while (true) {

            System.out.println("\n=========================================");
            System.out.println("      HOTEL RESERVATION SYSTEM");
            System.out.println("=========================================");

            System.out.println("1. View All Rooms");
            System.out.println("2. Search Rooms By Category");
            System.out.println("3. Book Room");
            System.out.println("4. View Reservations");
            System.out.println("5. Booking Details");
            System.out.println("6. Update Reservation");
            System.out.println("7. Cancel Reservation");
            System.out.println("8. Make Payment");
            System.out.println("9. View Payment History");
            System.out.println("0. Exit");

            System.out.print("\nEnter Choice : ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    roomService.viewAllRooms();
                    break;

                case 2:

                    System.out.println("\nChoose Category");

                    System.out.println("1. Standard");
                    System.out.println("2. Deluxe");
                    System.out.println("3. Suite");

                    System.out.print("Choice : ");

                    int categoryChoice = sc.nextInt();
                    sc.nextLine();

                    String category = "";

                    switch (categoryChoice) {

                        case 1:
                            category = "Standard";
                            break;

                        case 2:
                            category = "Deluxe";
                            break;

                        case 3:
                            category = "Suite";
                            break;

                        default:
                            System.out.println("Invalid Category");
                            continue;

                    }

                    roomService.searchRoomByCategory(category);
                    break;

                case 3:

                    reservationService.bookRoom(sc);
                    break;

                case 4:

                    reservationService.viewReservations();
                    break;

                case 5:

                    reservationService.bookingDetails(sc);
                    break;

                case 6:

                    reservationService.updateReservation(sc);
                    break;

                case 7:

                    reservationService.cancelReservation(sc);
                    break;

                case 8:

                    paymentService.makePayment(sc);
                    break;

                case 9:

                    paymentService.viewPayments();
                    break;

                case 0:

                    System.out.println("\nThank You For Using Hotel Reservation System.");

                    sc.close();

                    try {
                        con.close();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    System.exit(0);

                    break;

                default:

                    System.out.println("Invalid Choice.");

            }

        }

    }

}
