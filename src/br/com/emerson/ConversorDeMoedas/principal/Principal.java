package br.com.emerson.ConversorDeMoedas.principal;
import br.com.emerson.ConversorDeMoedas.API.ApiKey;
import br.com.emerson.ConversorDeMoedas.API.httpClient;
import br.com.emerson.ConversorDeMoedas.API.obtemCambio;
import br.com.emerson.ConversorDeMoedas.conversor.MapeamentoMoeda;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ApiKey apiKey = new ApiKey();
        apiKey.setApiKey("https://v6.exchangerate-api.com/v6/b68be7e7adae59698fcec8b0/latest/brl");
        httpClient httpClient = new obtemCambio();
        //MapeamentoMoeda nomeMoedas = new MapeamentoMoeda();

        System.out.println("""
                 ---- Opções ----
                1 - Fazer conversão de moeda.
                2 - Fazer conversão para até 3 moedas diferentes.
                3 - Sair.
                4 - Tipos de moedas aceitas.
                ------------------------
                """);


        while (true) {
            System.out.println("Escreva o opção desejada!");
            String busca = scanner.nextLine();

            if (busca.equals("3")) {
                System.out.println("Programa finalizado com sucesso");
                break;

            }else if (busca.equals("1") || busca.equals("2")) {
                HttpResponse<String> response = httpClient.fazRequisicao(apiKey.getApiKey());
                if (response.statusCode() != 200) {
                    System.out.println("Erro ao acessar API");

                }
                String json = response.body();
                Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                        .create();
                Map<String, Object> ratesMap = gson.fromJson(json, Map.class);
                Map<String, Double> rates = (Map<String, Double>) ratesMap.get("conversion_rates");

                System.out.println("Escreva a moeda de origem. (Ex: USD, BRL, EUR)");
                String origem = scanner.nextLine().toUpperCase();

                if (busca.equals("1")){

                System.out.println("Escreva a moeda de destino.");
                String destino = scanner.nextLine();


                System.out.println("Escreva o valor que deseja converter.");
                double dinheiro = scanner.nextDouble();
                scanner.nextLine();
                if (rates.containsKey(origem) && rates.containsKey(destino)) {
                    double valorConvertido = dinheiro * (rates.get(destino) / rates.get(origem));
                    System.out.printf("%.2f %s é igual a %.2f %s.%n", dinheiro, origem, valorConvertido, destino);
                    break;
                } else {
                    System.out.println("Uma das moedas está incorreta");
                    break;
                    }
                }
            }
            if (busca.equals("4")) {
                System.out.println("As moedas aceitas são: ");
                System.out.println(MapeamentoMoeda.exibirMoedas());
                System.out.println("O programa finalizou corretamente");
                break;

            }else if (busca.equals("2")) {
                HttpResponse<String> response = httpClient.fazRequisicao(apiKey.getApiKey());
                String json = response.body();
                Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                        .create();
                Map<String, Object> ratesMap = gson.fromJson(json, Map.class);
                Map<String, Double> rates = (Map<String, Double>) ratesMap.get("conversion_rates");
                Map<String, Double> ratesDois = (Map<String, Double>) ratesMap.get("conversion_rates");
                Map<String, Double> ratesTres = (Map<String, Double>) ratesMap.get("conversion_rates");

                System.out.println("Escreva a moeda de origem. (Ex: USD, BRL, EUR)");
                String origem = scanner.nextLine().toUpperCase();


                System.out.println("Escreva a primeira moeda de destino.");
                String destinoUm = scanner.nextLine().toUpperCase();


                System.out.println("Escreva a segunda moeda de destino.");
                String destinoDois = scanner.nextLine().toUpperCase();


                System.out.println("Escreva a terceira moeda de destino.");
                String destinoTres = scanner.nextLine().toUpperCase();


                System.out.println("Escreva o valor que deseja converter.");
                double dinheiro = scanner.nextDouble();
                scanner.nextLine();

                if (rates.containsKey(origem) && rates.containsKey(destinoUm) &&
                        ratesDois.containsKey(destinoDois) && ratesTres.containsKey(destinoTres)) {
                    double valorConvertido = dinheiro * (rates.get(destinoUm) / rates.get(origem));
                    double valorConvertidoDois = dinheiro * (ratesDois.get(destinoDois) / rates.get(origem));
                    double valorConvertidoTres = dinheiro * (ratesTres.get(destinoTres) / rates.get(origem));
                    System.out.printf("""
                            %.2f %s equivale a %.2f %s.%n
                            %.2f %s equivale a %.2f %s.%n
                            %.2f %s equivale a %.2f %s.%n
                           
                            """,
                            dinheiro, origem, valorConvertido, destinoUm,
                            dinheiro, origem, valorConvertidoDois, destinoDois,
                            dinheiro, origem, valorConvertidoTres, destinoTres);
                    System.out.println("O programa finalizou corretamente");
                    break;
                }
            }

        }

    }
}
