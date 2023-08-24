package com.example.hogwarts.controller;

import com.example.hogwarts.model.Avatar;
import com.example.hogwarts.service.AvatarService;
import com.example.hogwarts.service.AvatarServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/{id}/avatar ", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar (@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException{
        avatarService.uploadAvatar(id,avatar);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id){
        Avatar avatar = avatarService.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(avatar.getData());
    }
    @GetMapping(value = "/{id}/avatar")
    public void downloadAvatar (@PathVariable Long id, HttpServletResponse response)throws IOException
    {
        Avatar avatar = avatarService.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try(InputStream is = Files.newInputStream(path);
            OutputStream os = response.getOutputStream()){
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

    @GetMapping(value = "/{id}/avatar/getAllAvatar")
    public ResponseEntity<Avatar> getAllAvatar(@RequestParam("page") Integer pegeNumber, @RequestParam("size") Integer pageSize){
        List<Avatar> avatars = avatarService.getAllAvatar(pegeNumber, pageSize);
        return ResponseEntity.ok(avatars);
    }

}
