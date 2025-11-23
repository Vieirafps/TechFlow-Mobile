package com.techflowsupport.data.IA;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OpenAIClient {

    public interface IAListener {
        void onSuccess(String resposta);
        void onError(String erro);
    }

    private static final String TAG = "OpenAI";
    private static final String API_URL = "https://api.openai.com/v1/responses";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey;

    public OpenAIClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public void perguntarIA(String pergunta, IAListener listener) {

        try {
            JSONObject json = new JSONObject();
            json.put("model", "gpt-4o-mini");
            json.put("input", pergunta);

            RequestBody body = RequestBody.create(json.toString(), JSON);

            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(body)
                    .addHeader("Authorization", "Bearer " + apiKey)
                    .addHeader("Content-Type", "application/json")
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    listener.onError("Erro de conexão: " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    String jsonResposta = response.body().string();
                    Log.d(TAG, "RAW JSON → " + jsonResposta);

                    if (!response.isSuccessful()) {
                        listener.onError("Erro da API: " + response.code() + " → " + jsonResposta);
                        return;
                    }

                    try {
                        JSONObject obj = new JSONObject(jsonResposta);

                        // CORRETO PARA A API NOVA (obj.output[0].content[0].text)
                        String texto = obj
                                .getJSONArray("output")
                                .getJSONObject(0)
                                .getJSONArray("content")
                                .getJSONObject(0)
                                .getString("text");

                        listener.onSuccess(texto);

                    } catch (Exception e) {
                        listener.onError("Erro ao interpretar resposta: " + e.getMessage());
                    }
                }
            });

        } catch (Exception e) {
            listener.onError("Erro JSON: " + e.getMessage());
        }
    }
}
