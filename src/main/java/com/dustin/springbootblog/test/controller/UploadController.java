package com.dustin.springbootblog.test.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 配合application.properties
 * spring.servlet.multipart.max-file-size=50MB
 * spring.servlet.multipart.max-request-size=100MB
 * 調整每次請求最大SIZE&每個檔案最大SIZE
 * 
 * 文件上傳範例
 * @author dustinxie
 *
 */
@Controller
@RequestMapping("/admin")
public class UploadController {

	@GetMapping("/upload")
	public String uploadIndex() {
		return "admin/upload";
	}

	/**
	 * 範例 上傳的檔案放到server端指定位置
	 * 
	 * @param multipartFile
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("/upload")
	public String upload(@RequestParam(value = "file") MultipartFile multipartFile) throws IllegalStateException, IOException {
		String originalFilename = multipartFile.getOriginalFilename();
		multipartFile.transferTo(new File("E:\\BaiduNetdiskDownload\\" + originalFilename));
		return "admin/upload";
	}
	
	/**
	 * 範例 
	 * 上傳的多檔案放到server端指定位置
	 * 
	 * @param multipartFile
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("/uploadMultiFile")
	public String uploadMultiFile(@RequestParam(value = "file") MultipartFile[] multipartFiles) throws IllegalStateException, IOException {
		for (MultipartFile multipartFile : multipartFiles) {
			if (!multipartFile.isEmpty()) {
				multipartFile.transferTo(new File("E:\\BaiduNetdiskDownload\\" + multipartFile.getOriginalFilename()));
			}
		}
		return "admin/upload";
	}
}
