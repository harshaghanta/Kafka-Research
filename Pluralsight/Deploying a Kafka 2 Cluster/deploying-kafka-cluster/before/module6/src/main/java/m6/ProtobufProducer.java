package m6;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;

import java.util.Properties;

public class ProtobufProducer {
    public static void main(String[] args) throws Exception {

        var userId = 123124;

        RideHailingProtos.TripIntent tripIntent = RideHailingProtos.TripIntent.newBuilder()
                .setUserId(userId)
                .setLatLonFrom("48.2026,16.3688")
                .setLatLonTo("48.1869,16.3133")
                .build();


        var props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", IntegerSerializer.class.getName());

        // TODO 1: Add schema registry url
        // TODO 2: Use protobuf serializer
        // TODO 3: Change byte[] to TripIntent

        KafkaProducer<Integer, byte[]> producer = new KafkaProducer(props);

        ProducerRecord<Integer, byte[]> r =
            new ProducerRecord<>("trip-intent", userId, tripIntent.toByteArray());
        producer.send(r).get();
    }
}
