package de.juliuskreutz.web.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Controller
public class FileController {

    @GetMapping("/files/**")
    public String files(Model model, HttpServletRequest request) {

        addFiles(request.getServletPath().substring(1), model);

        return "files";
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam String location, @RequestParam String name) {

        if (location.startsWith("/") || location.contains(".") || name.contains("/")) return null;

        try {
            java.io.File file = new java.io.File(location + "/" + name);

            Path path = Path.of(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (IOException ignored) {
            System.out.println("Can't find file to download");
        }

        return null;
    }

    private void addFiles(String location, Model model) {
        try {
            model.addAttribute("folders", Files.list(Path.of(location))
                    .filter(Files::isDirectory)
                    .sorted()
                    .map(path -> new File(path.getFileName().toString(), path.toString()))
                    .collect(Collectors.toList()));

            model.addAttribute("files", Files.list(Path.of(location))
                    .filter(Files::isRegularFile)
                    .sorted()
                    .map(path -> new File(path.getFileName().toString(), location))
                    .collect(Collectors.toList()));

        } catch (IOException ignored) {
            System.out.println("Location doesn't exist");
        }
    }

    private static class File {
        public final String name;
        public final String location;

        public File(String name, String location) {
            this.name = name;
            this.location = location;
        }
    }
}
