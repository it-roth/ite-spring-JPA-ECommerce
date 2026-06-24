package co.istad.roth.ecommerce.features.file;

import co.istad.roth.ecommerce.features.file.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {
    FileUploadResponse upload(MultipartFile multipartFile);

    List<FileUploadResponse> uploadMultiple(List<MultipartFile> files);
    void delete(String name);


}
