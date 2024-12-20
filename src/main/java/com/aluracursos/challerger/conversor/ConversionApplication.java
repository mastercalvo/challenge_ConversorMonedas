package com.aluracursos.challerger.conversor;

import com.aluracursos.challerger.service.ConversionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
@Import(ConversionService.class)
@ComponentScan(basePackages = "com.aluracursos.challerger")
public class ConversionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConversionApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ConversionService conversionService) {
        return args -> {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.println("Bienvenido al Conversor de Monedas");
                System.out.print("Introduce la moneda de origen (por ejemplo, USD): ");
                String from = scanner.nextLine();

                System.out.print("Introduce la moneda de destino (por ejemplo, EUR): ");
                String to = scanner.nextLine();

                System.out.print("Introduce la cantidad a convertir: ");
                double amount = scanner.nextDouble();

                double rate = conversionService.obtenerTasaDeConversion(from, to);
                double convertedAmount = amount * rate;

                System.out.printf("La tasa de conversión de %s a %s es: %f%n", from, to, rate);
                System.out.printf("El monto convertido es: %f%n", convertedAmount);

            } catch (InputMismatchException e) {
                System.err.println("Error: La cantidad debe ser un número.");
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (RuntimeException e) {
                System.err.println("Error: No se pudo realizar la conversión debido a un problema con la API.");
            } finally {
                scanner.close();
            }
        };
    }
}
