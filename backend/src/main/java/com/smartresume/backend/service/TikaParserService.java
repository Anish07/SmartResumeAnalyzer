package com.smartresume.backend.service;

import org.apache.tika.exception.TikaException;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class TikaParserService {
    private final Tika tika = new Tika();

    public String parse(MultipartFile file) throws IOException, TikaException {
        return tika.parseToString(file.getInputStream());
    }
}
