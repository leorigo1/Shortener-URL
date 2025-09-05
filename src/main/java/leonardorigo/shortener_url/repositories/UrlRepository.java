package leonardorigo.shortener_url.repositories;

import leonardorigo.shortener_url.entities.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortUrl(String ShortUrl);

}
