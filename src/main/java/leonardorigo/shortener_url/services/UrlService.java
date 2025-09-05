package leonardorigo.shortener_url.services;

import leonardorigo.shortener_url.entities.Url;
import leonardorigo.shortener_url.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String ShortenUrl (String originalUrl) {
        String shortUrl = GenerateShortUrl();
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);
        url.setExpirationDate(LocalDateTime.now().plusDays(14));
        urlRepository.save(url);
        return shortUrl;
    }

    public Optional<Url> getOriginalUrl (String shortUrl) {
        Optional<Url> optionalUrl = urlRepository.findByShortUrl(shortUrl);
        if (optionalUrl.isPresent()) {
            Url url = optionalUrl.get();
            if (url.getExpirationDate().isAfter(LocalDateTime.now())) {
                return Optional.of(url);
            }
            else {
                urlRepository.delete(optionalUrl.get());
            }
        }
        return Optional.empty();
    }


    public String GenerateShortUrl () {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder ShortUrl = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            ShortUrl.append(characters.charAt(new Random().nextInt(characters.length())));
        }
        return ShortUrl.toString();
    }
}
