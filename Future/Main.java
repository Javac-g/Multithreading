public class Main{



    public static void main(String... args){



        CompletableFuture.supplyAsync(() -> {
            return fetchUser(123);  
        }).thenApply(user -> {
            return user.getEmail();  
        }).thenAccept(email -> {
            sendEmail(email);        
        
        System.out.println("Я не заблокирован!"); 

        var userFuture = CompletableFuture.supplyAsync(() -> getUser());
        var ordersFuture = CompletableFuture.supplyAsync(() -> getOrders());
        
        // Ждем оба, комбинируем результаты и возвращаем отчет
        userFuture.thenCombine(ordersFuture, (user, orders) -> {
            return new Report(user, orders);
        }).thenAccept(report -> show(report));

            
        CompletableFuture.supplyAsync(() -> heavyTask(), myCustomExecutor);

        
                            




    }
}
