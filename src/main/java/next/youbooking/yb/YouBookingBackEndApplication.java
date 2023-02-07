package next.youbooking.yb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YouBookingBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouBookingBackEndApplication.class, args);
//        new Thread(()->{
//            while(true){
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println("now is "+System.currentTimeMillis());
//            }
//
//        }).start();
    }

}
