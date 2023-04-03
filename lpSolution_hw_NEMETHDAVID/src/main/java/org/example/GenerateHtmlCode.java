package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GenerateHtmlCode {
    /**
     * Builds an HTML code using the command line arguments, removes specified tags, prints the resulting HTML code to the console, and saves it to a file named "output.html".
     *
     * @param args an array of command line arguments representing API keys and Name-Email address pairs
     */
    public void run(String[] args) {
        String html = buildHtmlCode(args);

        html = removeTags(html);
        System.out.println(html);

        writeHtmlCodeToFile(html);
    }

    /**
     * Builds an HTML code that displays a table with the given Name-Email address pairs.
     *
     * @param args the Name-Email address pairs
     * @return the HTML code as a string
     */
    private String buildHtmlCode(String[] args) {
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

        return htmlBuilder.toString();
    }

    /**
     * Removes the specified HTML elements from the given HTML code.
     *
     * @param htmlCode the HTML code to remove elements from
     * @return the HTML code with the specified elements removed
     */
    private String removeTags(String htmlCode) {
        Scanner tagRemoveScanner = new Scanner(System.in);
        System.out.println("Enter a coma-separated list of HTML elements to remove (h1,p,span) or type no if you don't want to remove any tags: ");
        String[] elementsToRemove = tagRemoveScanner.nextLine().split(",");
        if (elementsToRemove[0].equalsIgnoreCase("no") || elementsToRemove.length <= 0) {
            return htmlCode;
        }

        for (String element : elementsToRemove) {
            htmlCode = htmlCode.replaceAll("<" + element + "[^>]*>|</" + element + ">", "");
        }
        tagRemoveScanner.close();

        return htmlCode;
    }

    /**
     * Writes the given HTML code to a file named "output.html" in the current directory.
     *
     * @param htmlCode the HTML code to write to file
     */
    private void writeHtmlCodeToFile(String htmlCode) {
        try {
            File outputFile = new File("output.html");
            FileWriter fileWriter = new FileWriter(outputFile);
            fileWriter.write(htmlCode);
            fileWriter.close();
            System.out.println("HTML code saved to output.html");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the HTML code to file: " + e.getMessage());
        }
    }
}
