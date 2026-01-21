public class Main{



    public static void main(String... args){



        CompletableFuture.supplyAsync(() -> {
            return fetchUser(123);  
        }).thenApply(user -> {
            return user.getEmail();  
        }).thenAccept(email -> {
            sendEmail(email);        
        
        System.out.println("Я не заблокирован!"); 



        
                            




    }
}
