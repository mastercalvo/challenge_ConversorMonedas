# Conversor de Monedas

Este proyecto es un conversor de monedas desarrollado en Java utilizando Spring Boot. Permite convertir entre diferentes monedas utilizando tasas de cambio obtenidas de una API externa.

## Funcionalidades

- Conversión de Dólar a varias monedas: Peso argentino, Real brasileño y Peso colombiano.
- Conversión de Peso argentino, Real brasileño y Peso colombiano a Dólar.
- Manejo de errores para entradas inválidas y problemas con la API.
- Menú interactivo para seleccionar las conversiones.

## Estructura del Proyecto

- **ConversorMonedasApp**: Clase principal que maneja el flujo del programa y la interacción con el usuario.
- **ConversionService**: Servicio que se encarga de obtener las tasas de cambio desde la API externa.
- **LoggerService**: Servicio de registro de errores y eventos importantes.

### Cómo Ejecutar el Proyecto

1. Clonar el repositorio:
    ```sh
    git clone https://github.com/usuario/conversor-monedas.git
    ```

2. Navegar al directorio del proyecto:
    ```sh
    cd conversor-monedas
    ```

3. Compilar y ejecutar el proyecto:
    ```sh
    mvn spring-boot:run
    ```

4. Seguir las instrucciones en la consola para realizar las conversiones de monedas.

### Ejemplo de Uso

1. Seleccione una opción:
    - 1) Dólar => Peso argentino
    - 2) Peso argentino => Dólar
    - 3) Dólar => Real brasileño
    - 4) Real brasileño => Dólar
    - 6) Dólar => Peso colombiano
    - 7) Peso colombiano => Dólar
    - 8) Salir

2. Introduzca la cantidad a convertir:
    - El programa mostrará la tasa de cambio y la cantidad convertida.

### Manejo de Errores

- **Entrada Inválida**: Si se introduce un valor no numérico, el programa mostrará un mensaje de error y permitirá reintentar.
- **Problemas con la API**: Si hay un problema al obtener la tasa de cambio, se mostrará un mensaje de error y el programa se cerrará.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request para discutir cualquier cambio que desees realizar.

## Licencia

Este proyecto está licenciado bajo los términos de la [MIT License](LICENSE).
