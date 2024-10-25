package br.com.emerson.ConversorDeMoedas.API;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class obtemCambio implements httpClient{
    private final HttpClient client;

    public obtemCambio(){
        this.client = HttpClient.newHttpClient();
    }

    @Override
    public HttpResponse<String> fazRequisicao(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://v6.exchangerate-api.com/v6/b68be7e7adae59698fcec8b0/latest/brl")).build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
