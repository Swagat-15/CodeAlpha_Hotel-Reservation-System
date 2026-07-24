public class Reservation {

    private int reservationId;
    private String guestName;
    private String contactNo;
    private int roomNo;
    private String reservationDate;

    public Reservation() {
    }

    public Reservation(int reservationId, String guestName,
                       String contactNo, int roomNo,
                       String reservationDate) {

        this.reservationId = reservationId;
        this.guestName = guestName;
        this.contactNo = contactNo;
        this.roomNo = roomNo;
        this.reservationDate = reservationDate;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public String toString() {
        return "\nReservation ID : " + reservationId +
                "\nGuest Name : " + guestName +
                "\nContact No : " + contactNo +
                "\nRoom No : " + roomNo +
                "\nDate : " + reservationDate;
    }
}
