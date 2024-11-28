
import java.util.Scanner;

/**
 *
 * @author Irene Payá Hernández
 */
public class EndevinaParaula {

    public static void main(String[] args) {
        Scanner teclat = new Scanner(System.in);
        String[] palabrasFaciles = {"Casa", "Mesa", "Robot", "Cielo", "Flor", "Sol", "Amor", "Azul", "Reloj", "Piano"};
        String[] palabrasMedio = {"Bañera", "Estrella", "Maquina", "Sombrero", "Corazon", "Pintura", "Jardin", "Montaña", "Verano", "Secreto"};
        String[] palabrasDificiles = {"Calculadora", "Enciclopedia", "Television", "Destornillador", "Adivinanza", "Impresora", "Ambientador", "Bicicleta", "Tocadiscos", "Telescopio"};
        String[] palabras = new String[palabrasFaciles.length], ganar;
        String respuesta, palabra;
        int[] intentos, puntos;
        final int MINIMO_RONDAS = 1, MINIMO_INTENTOS = 5, INICIO = 0;
        int indicePalabra, cantidadIntentos = MINIMO_INTENTOS, cantidadPuntos, rondas = MINIMO_RONDAS, totalPuntos, dificultad;
        int contadorIntentos, contadorRondas, contadorPistas, contadorVocales, contadorConsonantes, restaPista = 0;
        char letra;
        boolean continuar, error, acertado, pista, salir;
        //Bienvenida y reglas
        System.out.println("BIENVENIDO A ADIVINA LA PALABRA");
        System.out.println("-------------------------------");
        System.out.print("\n¿Quieres conocer las reglas del juego?: ");
        respuesta = teclat.next().toUpperCase();

        while (!respuesta.equals("SI") && !respuesta.equals("NO")) {
            System.out.print("Introduce si/no:");
            respuesta = teclat.next().toUpperCase();
        }
        teclat.nextLine();
        if (respuesta.equals("SI")) {
            System.out.println("\nREGLAS DEL JUEGO");
            System.out.println("----------------");
            System.out.println("\n1.- Los puntos que recibas dependerán de la dificultad elegida, los intentos elegidos, los intentos sobrantes al terminar la ronda y las pistas utilizadas.");
            System.out.println("2.- Cada pista restará 20 puntos y cada intento sin utilizar sumara 50 puntos.");
            System.out.println("3.- Para palabras difíciles se empezara con una base de 2000 puntos entre la cantidad de intentos, para el nivel medio 1500p / intentos y para el nivel facil 1000p / intentos\n    por lo que si eliges menos intentos empezarás con más puntos de base, ATREVETE!");
            System.out.println("4.- Si quieres salir de la partida antes de tiempo solo tienes que escribir la palabra salir en cualquier intento.");
            System.out.println("5.- Cuando lleves la mitad de intentos las pistas ya no serán opcionales por lo que CUIDADO!! recuerda que las pistas quitan puntos!!");
            System.out.println("6.- Las palabras Difíciles tienen más de 8 letras, las de nivel medio tienen entre 6-8 y las de nivel fácil tienen menos de 6 letras.");
            System.out.println("7.- Para identificar las pistas aparecera una linea de asteriscos debajo de cada una.");
            System.out.println("8.- Si gastas los intentos tendrás automáticamente un total de 0 puntos en esa ronda.");
        }
        //Inicio del juego
        do {
            contadorRondas = INICIO;
            continuar = true;
            System.out.println("\nDIFICULTAD");
            System.out.println("-----------");
            System.out.println("1.- Fácil");
            System.out.println("2.- Medio");
            System.out.println("3.- Difícil");
            System.out.print("Introduce el nivel que quieres: ");
            while (!teclat.hasNextInt() || (dificultad = teclat.nextInt()) < 1 || dificultad > 3) {
                System.out.print("ERROR. Introduce una opción (1-3): ");
                teclat.nextLine();
            }
            teclat.nextLine();
            switch (dificultad) {
                case 1:
                    palabras = palabrasFaciles;
                    break;
                case 2:
                    palabras = palabrasMedio;
                    break;
                case 3:
                    palabras = palabrasDificiles;
                    break;
            }

            System.out.print("\n¿Cuántas rondas vas a jugar? ");
            while (!teclat.hasNextInt() || (rondas = teclat.nextInt()) < MINIMO_RONDAS) {
                if (rondas < MINIMO_RONDAS) {
                    System.out.print("ERROR. debe ser al menos 1 ronda: ");
                } else {
                    System.out.print("ERROR. Introduce un número entero: ");
                }
                rondas = MINIMO_RONDAS; //voy igualando rondas al mínimo para que se imprima bien el tipo de error que tiene el ususario
                teclat.nextLine();
            }
            teclat.nextLine();
            System.out.print("\n¿Cuántos intentos quieres como máximo en cada ronda? ");
            while (!teclat.hasNextInt() || (cantidadIntentos = teclat.nextInt()) < MINIMO_INTENTOS) {
                if (rondas < MINIMO_INTENTOS) {
                    System.out.print("ERROR. debe ser al menos 5 intentos: ");
                } else {
                    System.out.print("ERROR. Introduce un número entero: ");
                }
                cantidadIntentos = MINIMO_INTENTOS; //voy igualando cantidad de intentos al limite para que se imprima bien el tipo de error que tiene el ususario
                teclat.nextLine();
            }
            teclat.nextLine();
            intentos = new int[rondas];
            puntos = new int[rondas];
            ganar = new String[rondas];
            //inicio de la partida (reinicio de todo por ronda)
            do {
                salir = false;
                contadorIntentos = INICIO;
                contadorPistas = INICIO;
                switch (dificultad) {
                    case 1:
                        cantidadPuntos = 1000 / cantidadIntentos;
                        break;
                    case 2:
                        cantidadPuntos = 1500 / cantidadIntentos;
                        break;
                    default:
                        cantidadPuntos = 2000 / cantidadIntentos;
                        break;
                }
                acertado = false;
                indicePalabra = (int) (Math.random() * 10);
                if (contadorRondas == INICIO) {
                    System.out.println("\nEMPECEMOS!!");
                    System.out.println("-----------");
                }
                System.out.printf("\nRONDA %d!!\n", contadorRondas + 1);
                if(contadorRondas == rondas-1){
                    System.out.println("ÚLTIMA RONDA!!");
                }

                while (contadorIntentos < cantidadIntentos && !acertado && !salir) {
                    if (contadorIntentos == cantidadIntentos - 1) {
                        System.out.println("\n\nÚLTIMA OPORTUNDAD");
                        System.out.println("-----------------\n");
                    }
                    System.out.printf("INTENTO %d\n", contadorIntentos + 1);

                    do {
                        error = false;
                        System.out.print("Introduce una palabra: ");
                        palabra = teclat.next().toUpperCase().trim();
                        if (!palabra.matches("[A-Z]+")) {
                            error = true;
                            teclat.nextLine();
                        }
                    } while (error);
                    if (palabra.equalsIgnoreCase("salir")) {
                        salir = true;
                    }
                    if (palabra.equalsIgnoreCase(palabras[indicePalabra]) && !salir) {
                        System.out.println("\nENHORABUENA!! HAS ACERTADO!!");
                        acertado = true;
                        ganar[contadorRondas] = "has";
                        contadorIntentos++;
                    } else if (!salir) {
                        contadorIntentos++;
                        if (contadorIntentos < cantidadIntentos / 2) {
                            pista = false;
                            System.out.println("\nOhh, has fallado!!");
                            System.out.print("¿Quieres una pista? ");
                            respuesta = teclat.next().toUpperCase();
                            while (!respuesta.equals("SI") && !respuesta.equals("NO")) {
                                System.out.print("Introduce si/no:");
                                respuesta = teclat.next().toUpperCase();
                            }
                            teclat.nextLine();
                            if (respuesta.equals("SI")) {
                                pista = true;
                            }else{
                                System.out.println();
                            }
                        } else {
                            System.out.println("\nOhh, has fallado!!");
                            pista = true;
                        }

                        if (pista && contadorPistas < 3) {
                            switch (contadorPistas) {
                                case 0:
                                    contadorVocales = contadorConsonantes = INICIO;
                                    for (int j = 0; j < palabras[indicePalabra].length(); j++) {
                                        letra = palabras[indicePalabra].toUpperCase().charAt(j);
                                        if (letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U') {
                                            contadorVocales++;
                                        } else {
                                            contadorConsonantes++;
                                        }
                                    }
                                    System.out.printf("\nLa palabra tiene %d consonantes y %d vocales.\n", contadorConsonantes, contadorVocales);
                                    System.out.println("************************************************\n");
                                    contadorPistas++;
                                    break;
                                case 1:
                                    boolean repiten = false;
                                    for (int j = 0; j < palabras[indicePalabra].length() && !repiten; j++) {
                                        letra = palabras[indicePalabra].charAt(j);
                                        for (int k = j + 1; k < palabras[indicePalabra].length() && !repiten; k++) {
                                            if (letra == palabras[indicePalabra].charAt(k)) {
                                                repiten = true;
                                            }

                                        }

                                    }
                                    if (repiten) {
                                        System.out.println("\nLa palabra tiene letras que se repiten");
                                        System.out.println("************************************************\n");
                                    } else {
                                        System.out.println("\nLa palabra no tiene letras repetidas");
                                        System.out.println("************************************************\n");
                                    }
                                    contadorPistas++;
                                    break;
                                case 2:
                                    System.out.println();
                                    for (int j = 0; j < palabras[indicePalabra].length(); j++) {
                                        letra = palabras[indicePalabra].toUpperCase().charAt(j);
                                        if (letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U') {
                                            System.out.print("V");
                                        } else {
                                            System.out.print("C");
                                        }
                                    }
                                    System.out.println("");
                                    System.out.println("************************************************\n");
                                    contadorPistas++;
                                    break;
                            }
                        } else if (pista) {
                            if (palabras[indicePalabra].length() < palabra.length()) {
                                for (int j = 0; j < palabras[indicePalabra].length(); j++) {
                                    letra = palabras[indicePalabra].toUpperCase().charAt(j);
                                    if (letra == palabra.charAt(j)) {
                                        System.out.print(letra);
                                    } else {
                                        System.out.print("_");
                                    }

                                }
                                System.out.println();
                                System.out.println("************************************************\n");
                            }else{
                                for (int j = 0; j < palabra.length(); j++) {
                                    letra = palabras[indicePalabra].toUpperCase().charAt(j);
                                    if (letra == palabra.charAt(j)) {
                                        System.out.print(letra);
                                    } else {
                                        System.out.print("_");
                                    }
                                    restaPista = j;
                                }
                                for (int i = 0; i < (palabras[indicePalabra].length()- restaPista-1); i++) {
                                    System.out.print("_");
                                }
                                System.out.println();
                                System.out.println("************************************************\n");
                            }
                        }

                    }
                }
                if (!acertado && !salir) {
                    System.out.println("Oohh, has perdido :( ");
                    System.out.println("\nLa palabra era " + palabras[indicePalabra]);
                    ganar[contadorRondas] = "no has";
                    puntos[contadorRondas] = INICIO; //aprovechamos que inicio vale 0 
                }
                //guardamos resultados de la ronda en el historial
                if (!salir) { //si se ha esccrito salir, esa ronda no contará
                    intentos[contadorRondas] = contadorIntentos;                    
                    
                }
                if(acertado && !salir){
                    totalPuntos = cantidadPuntos + ((cantidadIntentos - contadorIntentos) * 50) - (contadorPistas * 20);
                    puntos[contadorRondas] = totalPuntos;
                }
                contadorRondas++;
                
            } while (contadorRondas < rondas && !salir);
            System.out.println("\nRESULTADOS DE LA PARTIDA");
            System.out.println("------------------------");
            if (contadorRondas != INICIO) {
                for (int i = 0; i < contadorRondas; i++) {
                    System.out.printf("En la ronda %d has realizado %d intentos y %s acertado la palabra por lo tanto has tenido un total de %d puntos\n", i + 1, intentos[i], ganar[i], puntos[i]);
                }
                System.out.println();
            } else {
                System.out.println("\nNo se ha jugado ninguna ronda.");
            }
            System.out.print("\n¿Quieres volver a jugar (si/no)? ");
            respuesta = teclat.next().toUpperCase();
            while (!respuesta.equals("SI") && !respuesta.equals("NO")) {
                System.out.print("Introduce si/no:");
                respuesta = teclat.next().toUpperCase();
            }
            teclat.nextLine();
            if (respuesta.equals("NO")) {
                continuar = false;
            }

        } while (continuar);
    }

}
