package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * ファイルアップロードのフォームクラス
 */
@Data
public class UploadForm {
    private MultipartFile multipartFile;
}