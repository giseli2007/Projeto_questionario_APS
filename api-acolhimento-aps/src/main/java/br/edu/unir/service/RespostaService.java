package br.edu.unir.service;

import br.edu.unir.model.RespostaQuestionario;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class RespostaService {

    public List<RespostaQuestionario> listarTodas() throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();

        //Busca todos os documentos da coleção
        ApiFuture<QuerySnapshot> query = db.collection("respostas_acolhimento").get();

        List<RespostaQuestionario> lista = new ArrayList<>();

        //Converte os documentos para objetos RespostaQuestionario
        for(QueryDocumentSnapshot document : query.get().getDocuments()) {
            lista.add(document.toObject(RespostaQuestionario.class));
        }
        return lista;
    }
    
    public String salvarResposta(RespostaQuestionario resposta) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            // respostas_acolhimento é o nome da coleção no Firebase onde as respostas serão armazenadas
            db.collection("respostas_acolhimento").add(resposta);
            return "Sucesso! Dados da APS salvos no Firebase.";
        } catch(Exception e) {
            return "Erro ao salvar no Firebase: " + e.getMessage();
        }
    }
}
