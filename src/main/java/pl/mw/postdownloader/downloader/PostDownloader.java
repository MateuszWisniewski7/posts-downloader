package pl.mw.postdownloader.downloader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.mw.postdownloader.dto.PostDTO;
import pl.mw.postdownloader.exception.PostsDownloadingException;

import java.util.Objects;

@Component
@Slf4j
public class PostDownloader implements Downloader {

    private final RestTemplate restTemplate;

    @Value("${api-url}")
    private String apiUrl;

    public PostDownloader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PostDTO[] download() {
        log.info("Downloading posts");
        ResponseEntity<PostDTO[]> response = restTemplate.getForEntity(apiUrl, PostDTO[].class);
        PostDTO[] body = response.getBody();
        if (response.getStatusCode().is2xxSuccessful() && Objects.nonNull(body)) {
            log.info("Successfully downloaded {} posts", body.length);
            return body;
        }
        log.error("Downloading posts failed with response {}", response);
        throw new PostsDownloadingException();
    }

}
