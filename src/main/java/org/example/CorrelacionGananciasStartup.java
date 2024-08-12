package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CorrelacionGananciasStartup {

    public static void main(String[] args) {
        String archivoCsv = "C:\\Users\\carlo\\Downloads\\startup-profit.csv";
        String linea = "";
        String separadorCsv = ",";
        ArrayList<Double> ganancias = new ArrayList<Double>();
        ArrayList<Double> gastoRd = new ArrayList<Double>();
        ArrayList<Double> gastoMarketing = new ArrayList<Double>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCsv))) {
            br.readLine();

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(separadorCsv);
                if (datos.length > 4) {
                    ganancias.add(Double.parseDouble(datos[4]));
                    gastoRd.add(Double.parseDouble(datos[0]));
                    gastoMarketing.add(Double.parseDouble(datos[2]));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        double correlacionGananciasRd = calcularCorrelacion(ganancias, gastoRd);
        double correlacionGananciasMarketing = calcularCorrelacion(ganancias, gastoMarketing);

        System.out.println("Correlación entre profit y R&D spend: " + correlacionGananciasRd);
        System.out.println("Correlación entre profit y Marketing spend: " + correlacionGananciasMarketing);
    }

    public static double calcularCorrelacion(ArrayList<Double> x, ArrayList<Double> y) {
        int n = x.size();

        double sumaX = 0.0;
        double sumaY = 0.0;
        double sumaXY = 0.0;
        double sumaCuadradaX = 0.0;
        double sumaCuadradaY = 0.0;

        for (int i = 0; i < n; i++) {
            sumaX += x.get(i);
            sumaY += y.get(i);
            sumaXY += x.get(i) * y.get(i);
            sumaCuadradaX += x.get(i) * x.get(i);
            sumaCuadradaY += y.get(i) * y.get(i);
        }

        double numerador = n * sumaXY - sumaX * sumaY;
        double denominador = Math.sqrt((n * sumaCuadradaX - sumaX * sumaX) * (n * sumaCuadradaY - sumaY * sumaY));

        if (denominador == 0) return 0;

        return numerador / denominador;
    }
}
