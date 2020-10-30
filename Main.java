import apoorv.Repository;

public class Main {
    public static void main(String[] args) throws Exception {
    
        Repository repository = new Repository();
        //repository.listAllCargoShips("to", "from");
        //repository.listAllCruiseShips("to","from");
        //repository.listAllUserBookings(1);
        Boolean b = repository.checkUserCredentials(1, "testUser2");
        System.out.println(b);
        b = repository.checkAdminCreadentials(1, "testAdmin1");
        System.out.println(b);

        System.out.println(repository.listAllCargoShips("mumbai", "tokyo"));
        
    }



}
