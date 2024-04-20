package com.myApi.translateApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@RestController
@SpringBootApplication
public class TranslateApiApplication {

    private final Translator translator;

    public TranslateApiApplication() {
        String languageFile = "src/main/resources/properties/ptbr_en.properties";
        this.translator = new Translator(languageFile);
    }

    public static void main(String[] args) {
        SpringApplication.run(TranslateApiApplication.class, args);
    }

    @GetMapping("/traduzir")
    public String traduzirPalavra(@RequestParam String palavra) {
        return translator.translateWord(palavra);
    }

}

class Translator {
    private Properties translations;

    public Translator(String languageFile) {
        translations = new Properties();
        try (InputStream input = new FileInputStream(languageFile)) {
            translations.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String translate(String word) {
        return translations.getProperty(word, "Translation not found");
    }

    public String translateWord(String wordToTranslate) {
        String word = wordToTranslate.toLowerCase();
        return translate(word);
    }
}
