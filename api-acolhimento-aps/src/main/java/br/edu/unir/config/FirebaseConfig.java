package br.edu.unir.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import java.io.FileInputStream;

@Configuration
public class FirebaseConfig {
    
    @PostConstruct
    public void init(){
        System.out.println(">>>>> ESTOU TENTANDO LIGAR O FIREBASE <<<<<");
        try {
            FileInputStream serviceAccount = 
                new FileInputStream("api-acolhimento-aps/src/main/resources/firebase-key.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Conexão com Firebase estabelecida!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
