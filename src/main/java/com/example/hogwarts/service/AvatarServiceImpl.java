package com.example.hogwarts.service;

import com.example.hogwarts.model.Avatar;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.repositories.AvatarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.EntityNotFoundException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
@Service
public class AvatarServiceImpl implements AvatarService {
    private final StudentService studentService;
    private final AvatarRepository avatarRepository;
    Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);


    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    public AvatarServiceImpl(StudentService studentService,
                             AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }

    @Override
    public Avatar findAvatar(Long studentId) {
        logger.info("Метод findAvatar запущен");
        return avatarRepository.findByStudentId(studentId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Avatar findOrCreateAvatar(Long studentId) {
        logger.info("Метод findOrCreteAvatar запущен");
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    @Override
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Метод uploadAvatar запущен");
        if (avatarFile != null) {
            Student student = studentService.get(studentId);
            Path filePath = buildFilePath(student, avatarFile.getOriginalFilename());
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);
            try (
                    InputStream is = avatarFile.getInputStream();
                    OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                    BufferedInputStream bis = new BufferedInputStream(is, 1024);
                    BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
            ) {
                bis.transferTo(bos);
            }
            Avatar avatar = findOrCreateAvatar(studentId);
            avatar.setStudent(student);
            avatar.setFilePath(filePath.toString());
            avatar.setFileSize(avatarFile.getSize());
            avatar.setMediaType(avatarFile.getContentType());
            avatar.setData(avatarFile.getBytes());
            avatarRepository.save(avatar);
        }
    }


    private String getExtensions(String fileName) {
        logger.info("Метод getExtensions запущен");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private Path buildFilePath(Student student, String fileName) {
        logger.info("Метод buildFilePath запущен");
        return Path.of(avatarsDir, student.getId() + "-" + student.getName() + "." +
                getExtensions(Objects.requireNonNull(fileName)));
    }
    public List<Avatar> getAllAvatar(Integer pegeNumber, Integer pageSize){
        logger.info("Метод getAllAvatar запущен");
        PageRequest pageRequest = PageRequest.of(pegeNumber - 1,pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }

}

