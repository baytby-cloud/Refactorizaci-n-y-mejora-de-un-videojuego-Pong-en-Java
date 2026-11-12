# Pong — Java Swing

## Descripción

Este proyecto consiste en un videojuego Pong desarrollado en Java utilizando Swing. El jugador controla una paleta para evitar que la pelota llegue al borde inferior de la pantalla.

El proyecto fue mejorado mediante la organización del código, el uso de `Timer` para actualizar el juego, controles con Key Bindings y pruebas unitarias con JUnit.

## Funcionalidades

- Movimiento automático de la pelota.
- Rebote de la pelota contra los laterales y la parte superior.
- Rebote de la pelota al entrar en contacto con la paleta.
- Reinicio de la pelota cuando llega al fondo de la pantalla.
- Movimiento de la paleta hacia la izquierda y derecha.
- Pausa y reanudación del juego.
- Límites para evitar que la paleta salga del área de juego.
- Pruebas unitarias para comprobar el comportamiento de la paleta.

## Tecnologías utilizadas

- Java.
- Java Swing y AWT.
- Gradle.
- JUnit 6.

## Estructura del proyecto

```text
src/
├── main/
│   └── java/
│       └── mx/
│           └── unison/
│               └── pong/
│                   ├── Main.java
│                   ├── Board.java
│                   ├── BallRunner.java
│                   └── Paddle.java
└── test/
    └── java/
        └── PaddleTest.java
```

## Descripción de las clases

### Main.java
Clase principal encargada de iniciar la aplicación y mostrar la ventana del juego.

### Board.java
Componente encargado de dibujar los elementos del juego, configurar los controles del teclado y actualizar la pantalla mediante un `Timer`.

El temporizador realiza actualizaciones cada 16 milisegundos aproximadamente.

### BallRunner.java
Controla el movimiento de la pelota y las colisiones.

- La velocidad horizontal es de 5 píxeles por actualización.
- La velocidad vertical es de 5 píxeles por actualización.
- Detecta colisiones con los bordes y la paleta.
- Reinicia la pelota cuando llega al fondo.

### Paddle.java
Representa la paleta del jugador. Controla su movimiento y evita que salga de los límites del tablero.

### PaddleTest.java
Contiene cinco pruebas unitarias para comprobar la posición inicial de la paleta, sus movimientos y sus límites.

## Controles del juego

| Tecla | Función |
|---|---|
| Flecha izquierda | Mover la paleta a la izquierda |
| Flecha derecha | Mover la paleta a la derecha |
| Espacio | Pausar el juego |
| R | Reanudar el juego |

## Ejecución del proyecto

1. Abrir el proyecto en IntelliJ IDEA.
2. Esperar a que Gradle termine de sincronizar.
3. Abrir la clase `Main.java`.
4. Ejecutar el método `main`.

## Pruebas unitarias

El proyecto utiliza JUnit 6 para realizar pruebas sobre la clase `Paddle`.

Las cinco pruebas verifican:

1. Que la paleta inicia en el centro.
2. Que la paleta se mueve hacia la derecha.
3. Que la paleta se mueve hacia la izquierda.
4. Que la paleta no puede salir por el límite izquierdo.
5. Que la paleta no puede salir por el límite derecho.

**Resultado:** Las cinco pruebas se ejecutaron correctamente.

Para ejecutar las pruebas desde IntelliJ IDEA, se puede utilizar la opción de ejecución de pruebas de Gradle o ejecutar la clase `PaddleTest`.

Pruebas funcionales

Se ejecutaron 12 casos de prueba para verificar el funcionamiento del videojuego Pong. Se comprobó el inicio, pausa y reanudación, cierre de la aplicación, colisiones de la pelota, movimiento de la paleta y reinicio de la pelota.
## Mejoras realizadas

- Se organizó la actualización del juego mediante un `Timer` de Swing.
- Se implementaron Key Bindings para controlar el juego.
- Se agregaron límites al movimiento de la paleta.
- Se mejoró la detección de colisiones.
- Se agregaron pruebas unitarias con JUnit.

## Conclusión

La refactorización permitió mejorar la organización y el funcionamiento del videojuego Pong. Se implementaron controles más adecuados, actualizaciones periódicas mediante `Timer` y pruebas unitarias para verificar el comportamiento de la paleta.

El proyecto permite aplicar conceptos de programación orientada a objetos, interfaces gráficas, eventos y pruebas de software.