import db.DB;

public class Main {
    public static void main(String[] args) {
        DB.getConnection();
        DB.closeConnection();
    }
}