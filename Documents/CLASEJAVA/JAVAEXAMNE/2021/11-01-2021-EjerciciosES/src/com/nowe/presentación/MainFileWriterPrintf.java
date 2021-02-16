/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nowe.presentación;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Programacion
 */
public class MainFileWriterPrintf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Leer por pantalla en un bucle 
        El dni del alumno, el nombre y la nota
         */

        // 1. Declaración de variables
        Scanner s1 = new Scanner(System.in);
        String nFichero = null, respuesta, numDNI, nombre, linea;
        FileWriter fSalida;
        boolean anadir;
        double nota;

        // 2. Solicitar si se crea de nuevo el fichero o se quiere añadir informacion  y el nombre del fichero
        System.out.println("¿Quiere añadir información a un fichero determinado (la variable es boolean) -->");
        anadir = s1.nextBoolean();
        s1.nextLine();
        System.out.println("Escriba el nombre del fichero ya sea para añadir o crear uno nuevo --> ");
        nFichero = s1.nextLine();
        try {
            // Instanciar el objeto PrintWriter para escribir el fichero
            fSalida = new FileWriter(nFichero, anadir);
            // Mandar llamar a la rutina del cabecero
            if (!anadir) {
                cabecero(fSalida);
            }

            do {
                System.out.println("Escribir el DNI del alumno -->");
                numDNI = s1.nextLine();
                System.out.println("Escribir el nombre del alumno --> ");
                nombre = s1.nextLine();
                nota = leerNumero();
                linea = String.format("%-10s %-25s \t %.2f%n", numDNI, nombre, nota);
                fSalida.write(linea);
                System.out.println("Desea continuar grabando notas (S/N) ? -->");
                respuesta = s1.nextLine();
            } while (respuesta.equalsIgnoreCase("s"));

            fSalida.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }

    public static double leerNumero() {
        double numero;
        Scanner teclado = new Scanner(System.in);

        while (true) {
            System.out.print("Escriba un número --> ");
            try {
                numero = teclado.nextDouble();
            } catch (InputMismatchException x) {
                System.out.println(x.toString());
                System.out.println("Intentelo otra vez");
                teclado.next();
                continue;
            }
            break;
        }
        return numero;

    }

    public static void cabecero(FileWriter fS) {
        try {
            for (int i = 0; i < 55; i++) {
                fS.write("-");
            }
            fS.write("\n");
            fS.write("DNI \t  Nombre \t \t Nota\n");
            for (int i = 0; i < 55; i++) {
                fS.write("-");
            }
            fS.write("\n");
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
}
