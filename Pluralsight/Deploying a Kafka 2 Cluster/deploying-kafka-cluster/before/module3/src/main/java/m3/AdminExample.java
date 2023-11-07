package m3;

import org.apache.kafka.clients.admin.Admin;

import java.util.List;
import java.util.Map;

public class AdminExample {
    public static void main(String[] args) throws Exception {

        Admin admin = Admin.create(
                Map.of("bootstrap.servers", "localhost:9092")
        );

        printAllTopics(admin);
        // TODO: create a 'booking-created' topic
        printAllTopics(admin);
    }

    static void printAllTopics(Admin client) throws Exception {
        // TODO: Get the actual topics
        var topicNames = List.of("");
        System.out.println("Topics in the cluster:");
        topicNames.forEach(System.out::println);
        System.out.println();
    }
}
