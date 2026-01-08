package server;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;

import java.util.List;
import java.util.Map;

public class DockerService {

    private DockerClient docker = DockerClientBuilder.getInstance().build();

    public Map<String, Object> listImages() {
        List<?> images = docker.listImagesCmd().exec();
        return Map.of("status", "ok", "data", images);
    }

    public Map<String, Object> pullImage(String image) {
        docker.pullImageCmd(image).start();
        return Map.of("status", "ok", "message", "Téléchargement lancé");
    }

    public Map<String, Object> runContainer(String image, String name) {
        String id = docker.createContainerCmd(image)
                .withName(name)
                .exec()
                .getId();

        docker.startContainerCmd(id).exec();

        return Map.of("status", "ok", "containerId", id);
    }
}
