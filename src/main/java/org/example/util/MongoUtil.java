package util;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;

public class MongoUtil {

    private static MongoClient client;
    private static MongoDatabase db;

    public static MongoDatabase getDatabase() {
        if (db == null) {
            // 1. Configurar el codec para POJOs (Mapeo automático)
            CodecRegistry pojoCodecRegistry = fromRegistries(
                    getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build())
            );

            // 2. Crear el cliente (Singleton)
            // Asegúrate de que tu MongoDB corre en el puerto 27017
            client = MongoClients.create("mongodb://localhost:27017");

            // 3. Obtener la base de datos aplicando el registro de codecs
            db = client.getDatabase("blog_db").withCodecRegistry(pojoCodecRegistry);
        }
        return db;
    }

    public static void close() {
        if (client != null) {
            client.close();
            db = null; // Reseteamos para permitir reconexión si fuera necesario
            System.out.println("Conexión con MongoDB cerrada.");
        }
    }
}