package br.com.emerson.ConversorDeMoedas.API;

import java.net.http.HttpResponse;

public interface httpClient {
    HttpResponse<String> fazRequisicao(String ApyKey) throws Exception;
}
