package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable {

    private Socket socket;
    private DockerService dockerService = new DockerService();
    private ObjectMapper mapper = new ObjectMapper();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String request;
            while ((request = in.readLine()) != null) {

                Map<String, Object> json = mapper.readValue(request, Map.class);
                String action = (String) json.get("action");

                Object response;

                switch (action) {
                    case "list_images":
                        response = dockerService.listImages();
                        break;

                    case "pull_image":
                        response = dockerService.pullImage((String) json.get("image"));
                        break;

                    case "run_container":
                        response = dockerService.runContainer(
                                (String) json.get("image"),
                                (String) json.get("name")
                        );
                        break;

                    default:
                        response = Map.of("status", "error", "message", "Commande inconnue");
                }

                out.println(mapper.writeValueAsString(response));
            }

        } catch (Exception e) {
            System.out.println("Client déconnecté.");
        }
    }
}