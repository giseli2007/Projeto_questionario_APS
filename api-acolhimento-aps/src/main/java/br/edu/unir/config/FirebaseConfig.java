package br.edu.unir.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    
    @PostConstruct
    public void init() {
        System.out.println(">>>>> ESTOU TENTANDO LIGAR O FIREBASE <<<<<");
        
        try {
            InputStream serviceAccount;
            String firebaseKeyJson = System.getenv("FIREBASE_KEY_JSON");

            if (firebaseKeyJson == null) {
                // Modo Desenvolvimento: lê o arquivo do PC
                serviceAccount = getClass().getClassLoader().getResourceAsStream("/firebase-key.json");
            } else {
                // Modo Nuvem: lê a variável de ambiente que vai configurar no Render
                serviceAccount = new ByteArrayInputStream(firebaseKeyJson.getBytes());
            }

            // Agora o serviceAccount está visível aqui fora
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