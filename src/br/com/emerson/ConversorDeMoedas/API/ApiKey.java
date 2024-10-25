package br.com.emerson.ConversorDeMoedas.API;

public class ApiKey {
    private String apiKey;

    public ApiKey() {
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    @Override
    public String toString() {
        return "ApiKey{" +
                "apiKey='" + apiKey + '\'' +
                '}';
    }


}

