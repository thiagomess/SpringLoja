package br.com.casadocodigo.loja.infra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Teste usando API de CEP via GET
public class ClienteViaCepWS {

    public static String buscarCep(String cep) {
        String json;

        try {
            URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();
             
            String linha = br.readLine();

            while (linha != null) {

            	jsonSb.append(linha.trim());
            	linha = br.readLine();
			}

            json = jsonSb.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return json;
    }

    public static void main(String[] args) throws IOException {
        String json = buscarCep("05877250");
        System.out.println(json);

        Map<String,String> mapa = new HashMap<>();

        Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(json);
        while (matcher.find()) {
            String[] group = matcher.group().split(":");
            mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
        }

        System.out.println(mapa.get("uf"));
        System.out.println(mapa);
    }
}