
import java.util.Scanner;

/**
 *
 * @author Irene Payá Hernández
 */
public class UT3Problema4 {

    public static void main(String[] args) {
        Scanner teclat = new Scanner(System.in);
        double resultat = 0;
        String dadesUsuari, valor1, valor2, operador;
        char validacio;
        String[] operacio, aux;
        boolean continuar = true, error;
        int contador = 0, enter1 = 0, enter2 = 0;
        operacio = new String[3];
        System.out.println("-----------");
        System.out.println("CALCULADORA");
        System.out.println("-----------\n");
        do {
            error = false;
            System.out.print("Introdueix una operació (END per acabar): ");
            dadesUsuari = teclat.nextLine();
            aux = dadesUsuari.split(" ");

            for (int i = 0; i < aux.length; i++) {
                if (aux[i].equals("END")) {
                    continuar = false;
                }
                if (!aux[i].isBlank() && contador < 3) {
                    operacio[contador] = aux[i];
                    contador++;
                }
            }

            if (continuar) {
                valor1 = operacio[0];
                operador = operacio[1];
                valor2 = operacio[2];
                for (int i = 0; !error && i < valor1.length(); i++) {
                    validacio = valor1.charAt(i);
                    if (validacio != '0' && validacio != '1' && validacio != '2' && validacio != '3' && validacio != '4' && validacio != '5' && validacio != '6' && validacio != '7' && validacio != '8' && validacio != '9') {
                        error = true;
                    }
                }
                for (int i = 0; !error && i < valor2.length(); i++) {
                    validacio = valor2.charAt(i);
                    if (validacio != '0' && validacio != '1' && validacio != '2' && validacio != '3' && validacio != '4' && validacio != '5' && validacio != '6' && validacio != '7' && validacio != '8' && validacio != '9') {
                        error = true;
                    }
                }

                if (!error) {
                    enter1 = Integer.parseInt(valor1);
                    enter2 = Integer.parseInt(valor2);
                    switch (operador) {
                        case "+":
                            resultat = enter1 + enter2;
                            break;
                        case "-":
                            resultat = enter1 - enter2;
                            break;
                        case "/":
                            if (enter2 == 0) {
                                error = true;
                            } else {
                                resultat = (double) (enter1 / enter2); // lo hago así para que el resultado sea más exacto y salga redondeado no truncado
                            }
                            break;
                        case "*":
                            resultat = enter1 * enter2;
                        default:
                            error = true;
                            break;
                    }
                }

                if (error) {
                    System.out.println("No és una operació\n");
                } else {
                    System.out.printf("%d %s %d = %.0f\n\n", enter1, operador, enter2, resultat);
                }

            }
        } while (continuar);
    }
}
