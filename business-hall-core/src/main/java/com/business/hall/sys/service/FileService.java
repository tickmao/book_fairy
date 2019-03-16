package com.business.hall.sys.service;

import java.io.IOException;

import com.business.hall.sys.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	FileInfo save(MultipartFile file) throws IOException;

	void delete(String id);

}
