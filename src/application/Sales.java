package application;

import entities.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Sales {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        //Diretorio de Entrada
        String pathIn = "C:\\temp\\in\\sales.csv";

        //Diretorio raiz para criação da pasta de saída
        String path = "C:\\temp\\in";

        //Instancia um objeto File com o caminho raiz
        File pathFile = new File(path);

        //Armazena em uma variavel o diretorio raiz
        String pathOut = pathFile.getParent();

        //Cria o diretorio de saida 'out'
        boolean success = new File(pathOut + ".\\out").mkdir();

        //Especifica o nome do arquivo de saída
        String out = pathOut + "\\out\\summary.csv";

        List<Product> list = new ArrayList<>();

        //Faz a leitura do arquivo de entrada
        try (BufferedReader br = new BufferedReader(new FileReader(pathIn))) {
            String item = br.readLine();
            while (item != null) {
                String[] fields = item.split(",");
                String name = fields[0];
                double price = Double.parseDouble(fields[1]);
                int quantity = Integer.parseInt(fields[2]);

                list.add(new Product(name, price, quantity));
                item = br.readLine();
            }

            //Escrever o arquivo de saída
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {
                for (Product p : list) {
                    bw.write(p.getName() + ", " + String.format("%.2f", p.total()));
                    bw.newLine();
                }
                System.out.println(out + " Created");

            } catch (IOException e) {
                System.out.println("Error writing file: " + e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
