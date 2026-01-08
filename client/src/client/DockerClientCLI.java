package client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class DockerClientCLI {

    public static void main(String[] args) {

        String serverIp = "10.145.44.124";
        int serverPort = 5000;

        try (
            Socket socket = new Socket(serverIp, serverPort);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Connecté au serveur Docker à " + serverIp);

            while (true) {
                System.out.print("docker> ");
                String command = scanner.nextLine();

                if (command.equalsIgnoreCase("exit")) {
                    System.out.println("Déconnexion...");
                    break;
                }

                out.println(command);

                String response = in.readLine();
                System.out.println("Réponse serveur : " + response);
            }

        } catch (Exception e) {
            System.err.println("Erreur de connexion au serveur");
            e.printStackTrace();
        }
    }
}
