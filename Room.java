public class Room {

    private int roomNo;
    private String category;
    private double price;
    private String status;

    public Room() {
    }

    public Room(int roomNo, String category, double price, String status) {
        this.roomNo = roomNo;
        this.category = category;
        this.price = price;
        this.status = status;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room No : " + roomNo +
                "\nCategory : " + category +
                "\nPrice : ₹" + price +
                "\nStatus : " + status;
    }
}