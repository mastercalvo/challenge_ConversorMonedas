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

        System.out.println("********************************************");
        System.out.println("Sea bienvenido/a al Conversor de Moneda = ]");
        System.out.println("********************************************");

        while (running) {
            try {
                mostrarMenu();
                int opcion = scanner.nextInt();

                if (opcion == 8) {
                    manejarOpcionSalir();
                    running = false;
                } else {
                    manejarOpcion(opcion, scanner);
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: La entrada debe ser un número. Por favor, intente de nuevo.");
                scanner.nextLine(); // Limpiar el scanner
            } catch (NoSuchElementException e) {
                System.out.println("Error: No se proporcionó ninguna entrada. Por favor, intente de nuevo.");
            } catch (Exception e) {
                loggerService.error("Error inesperado: " + e.getMessage(), e);
                System.out.println("Error inesperado: Por favor, intente de nuevo.");
            }
        }
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("********************************************");
        System.out.println("Seleccione una opción:");
        System.out.println("1) Dólar => Peso argentino");
        System.out.println("2) Peso argentino => Dólar");
        System.out.println("3) Dólar => Real brasileño");
        System.out.println("4) Real brasileño => Dólar");
        System.out.println("6) Dólar => Peso colombiano");
        System.out.println("7) Peso colombiano => Dólar");
        System.out.println("8) Salir");
        System.out.println("********************************************");
        System.out.print("Seleccione una opción: ");
    }

    private void manejarOpcion(int opcion, Scanner scanner) {
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
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                return;
        }

        System.out.print("Introduce la cantidad a convertir: ");
        try {
            cantidad = scanner.nextDouble();
            realizarConversion(monedaOrigen, monedaDestino, cantidad);
        } catch (InputMismatchException e) {
            System.out.println("Error: La entrada debe ser un número. Por favor, intente de nuevo.");
            scanner.nextLine(); // Limpiar el scanner
        }
    }

    private void realizarConversion(String monedaOrigen, String monedaDestino, double cantidad) {
        try {
            double tasaConversion = conversionService.obtenerTasaDeConversion(monedaOrigen, monedaDestino);
            double resultado = cantidad * tasaConversion;
            System.out.println("********************************************");
            System.out.printf("La tasa de cambio es: %.4f%n", tasaConversion);
            System.out.printf("La cantidad de %.2f %s en %s es: %.2f%n", cantidad, monedaOrigen, monedaDestino, resultado);
            System.out.println("********************************************");
        } catch (Exception e) {
            loggerService.error("Error al realizar la conversión: " + e.getMessage(), e);
            System.out.println("Error: No se pudo realizar la conversión debido a un problema con la API.");
        }
    }

    private void manejarOpcionSalir() {
        System.out.println("Gracias por usar el Conversor de Monedas. ¡Hasta luego!");
        System.exit(0); // Cerrar el programa correctamente
    }
}
