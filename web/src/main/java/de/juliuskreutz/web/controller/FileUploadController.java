package de.juliuskreutz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class FileUploadController {

    @PostMapping("/upload")
    public void uploadFile(@RequestParam MultipartFile file, HttpServletResponse response) {

        long size = file.getSize() / 1048576;

        if (size > PropertiesController.getMaxFileSize()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        if (saveFile(file))
            response.setStatus(HttpServletResponse.SC_OK);
        else
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    private boolean saveFile(MultipartFile file) {
        try {
            Path savePath = Paths.get(PropertiesController.getFileSavePath() + File.separator + file.getOriginalFilename());
            Files.copy(file.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ignored) {
            System.out.println("Couldn't save file");
            return false;
        }
        return true;
    }

}
