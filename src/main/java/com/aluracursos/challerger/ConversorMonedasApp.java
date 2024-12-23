package com.aluracursos.challerger;

import com.aluracursos.challerger.service.ConversionService;
import com.aluracursos.challerger.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Component
public class ConversorMonedasApp implements CommandLineRunner {

    private final ConversionService conversionService;
    private final LoggerService loggerService;

    @Autowired
    public ConversorMonedasApp(ConversionService conversionService, LoggerService loggerService) {
        this.conversionService = conversionService;
        this.loggerService = loggerService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("********************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda = ]");
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileño");
            System.out.println("4) Real brasileño => Dólar");
            System.out.println("6) Dólar => Peso colombiano");
            System.out.println("7) Peso colombiano => Dólar");
            System.out.println("8) Salir");
            System.out.println("********************************************");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                double cantidad;
                String monedaOrigen = "";
                String monedaDestino = "";

                switch (opcion) {
                    case 1:
                        monedaOrigen = "USD";
                        monedaDestino = "ARS";
                        break;
                    case 2:
                        monedaOrigen = "ARS";
                        monedaDestino = "USD";
                        break;
                    case 3:
                        monedaOrigen = "USD";
                        monedaDestino = "BRL";
                        break;
                    case 4:
                        monedaOrigen = "BRL";
                        monedaDestino = "USD";
                        break;
                    case 6:
                        monedaOrigen = "USD";
                        monedaDestino = "COP";
                        break;
                    case 7:
                        monedaOrigen = "COP";
                        monedaDestino = "USD";
                        break;
                    case 8:
                        running = false;
                        System.out.println("Gracias por usar el Conversor de Monedas. ¡Hasta luego!");
                        continue;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                        continue;
                }

                if (running) {
                    System.out.print("Introduce la cantidad a convertir: ");
                    cantidad = scanner.nextDouble();

                    try {
                        double tasaConversion = conversionService.obtenerTasaDeConversion(monedaOrigen, monedaDestino);
                        double resultado = cantidad * tasaConversion;
                        System.out.printf("La cantidad de %.2f %s en %s es: %.2f%n", cantidad, monedaOrigen, monedaDestino, resultado);
                    } catch (Exception e) {
                        loggerService.error("Error al realizar la conversión: " + e.getMessage(), e);
                        System.out.println("Error: No se pudo realizar la conversión debido a un problema con la API.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: La entrada debe ser un número.");
                scanner.nextLine(); // Limpiar el scanner
            } catch (NoSuchElementException e) {
                System.out.println("Error: No se proporcionó ninguna entrada.");
            }
        }
        scanner.close();
    }
}
