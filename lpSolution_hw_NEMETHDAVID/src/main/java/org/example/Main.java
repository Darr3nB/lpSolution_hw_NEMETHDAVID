package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html><html><head><title>Németh Dávid - Teszt Feladat</title></head><body>");
        htmlBuilder.append("<h1>Teszt Feladat</h1><p><a href=\"https://github.com/Darr3nB/lpSolution_hw_NEMETHDAVID\">Megoldás</a></p>");
        htmlBuilder.append("<p>Németh Dávid Tel.: +36309937525 E-mail: david.nemeth024@gmail.com LinkedIn: https://www.linkedin.com/in/d%C3%A1vid-n%C3%A9meth-08401024b/</p>");
        htmlBuilder.append("<table border=\"1 px solid black\">");

        for (int i = 0; i < args.length; i += 2) {
            String name = args[i];
            String mail = args[i + 1];

            htmlBuilder.append(String.format("<tr><td>Név</td><td>%s</td></tr><tr><td>Elérhetőség</td><td>%s</td></tr>", name, mail));
        }

        htmlBuilder.append("</table><a href=\"http://lpsolutions.hu\">L&P Solutions</a></body></html>");
        String html = htmlBuilder.toString();

        Scanner tagRemoveScanner = new Scanner(System.in);
        System.out.println("Enter a coma-separated list of HTML elements to remove (h1,p,span): ");
        String[] elementsToRemove = tagRemoveScanner.nextLine().split(",");

        for (String element : elementsToRemove) {
            html = html.replaceAll("<" + element + "[^>]*>|</" + element + ">", "");
        }
        tagRemoveScanner.close();

        System.out.println(html);

        try {
            File outputFile = new File("output.html");
            FileWriter fileWriter = new FileWriter(outputFile);
            fileWriter.write(html);
            fileWriter.close();
            System.out.println("HTML code saved to output.html");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the HTML code to file: " + e.getMessage());
        }
    }
}