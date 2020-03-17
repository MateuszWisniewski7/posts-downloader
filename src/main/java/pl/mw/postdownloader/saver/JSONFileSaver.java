package pl.mw.postdownloader.saver;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.mw.postdownloader.downloader.PostDownloader;
import pl.mw.postdownloader.dto.PostDTO;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
@Slf4j
public class JSONFileSaver implements FileSaver {

    private final PostDownloader postDownloader;
    private final ObjectMapper objectMapper;

    @Value("${save-path}")
    private String savePath;

    public JSONFileSaver(PostDownloader postDownloader, ObjectMapper objectMapper) {
        this.postDownloader = postDownloader;
        this.objectMapper = objectMapper;
    }

    @Override
    @PostConstruct
    public void save() {
        PostDTO[] posts = postDownloader.download();
        log.info("Start saving posts as JSON files");
        for (PostDTO post : posts) {
            saveAsJson(post);
        }
        log.info("Saving posts finished");
    }

    private void saveAsJson(PostDTO post) {
        try {
            objectMapper.writeValue(new File(savePath + post.getId() + ".json"), post);
        } catch (IOException e) {
            log.error("Error while saving post", e);
        }
    }
}
