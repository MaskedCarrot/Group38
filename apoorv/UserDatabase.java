class UserDatabase{
    public void insertUser(int userId , long phoneNumber , String name , int age , String password , String email){

    }
    public void update(int userId){

    }
    public void check(String email , String password){
        Random rand = new Random();
        boolean status = rand.nextInt(10)%2 == 1?true : false;
        return status;
    }
    public static void main(String[] args) {
        Random rand = new Random();
        boolean b = rand(10)%2 == 1?true : false;
        System.out.println(b);
    }
}