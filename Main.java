import Apoorv.Repository;

public class Main {
    public static void main(String[] args) throws Exception {
    
        Repository repository = new Repository();
        repository.listAllCargoShips("to", "from");
        repository.listAllCruiseShips("to","from");
        //repository.listAllUserBookings(1);
    }
}
