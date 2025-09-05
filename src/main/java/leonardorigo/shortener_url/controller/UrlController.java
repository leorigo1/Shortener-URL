package leonardorigo.shortener_url.controller;

import leonardorigo.shortener_url.entities.Url;
import leonardorigo.shortener_url.repositories.UrlRepository;
import leonardorigo.shortener_url.services.UrlService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<Map<String, String>> shortenUrl (@RequestBody Map<String, String> request) {
        String originalUrl = request.get("url");
        String shortUrl = urlService.ShortenUrl(originalUrl);
        Map<String, String> response = new HashMap<String, String>();
        response.put("url", "localhost:8081/" + shortUrl);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Url> RedirectToOriginalUrl (@PathVariable String shortUrl) {
        Optional<Url> urlOptional = urlService.getOriginalUrl(shortUrl);
        if (urlOptional.isPresent()) {
            Url url = urlOptional.get();
            System.out.println("Redirecionando para = " + url.getOriginalUrl());
            return ResponseEntity.status(302).location(URI.create(url.getOriginalUrl())).build();
        }
        System.out.println("URL não encontrada ou expirada de: " + shortUrl);
        return ResponseEntity.notFound().build();
    }
}
