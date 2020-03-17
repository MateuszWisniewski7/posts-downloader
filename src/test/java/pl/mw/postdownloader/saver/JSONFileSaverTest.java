package pl.mw.postdownloader.saver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import pl.mw.postdownloader.downloader.PostDownloader;
import pl.mw.postdownloader.dto.PostDTO;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class JSONFileSaverTest {

    @Mock
    PostDownloader postDownloader;
    @Mock
    ObjectMapper objectMapper;
    @InjectMocks
    JSONFileSaver jsonFileSaver;

    @BeforeEach
    void setup() {
        String savePath = "C:\\Program Files\\shared\\postdownloader\\";
        ReflectionTestUtils.setField(jsonFileSaver, "savePath", savePath);
    }

    @Test
    void save() throws IOException {
        PostDTO[] posts = new PostDTO[1];
        PostDTO post = new PostDTO();
        post.setId(1);
        posts[0] = post;
        given(postDownloader.download()).willReturn(posts);

        jsonFileSaver.save();

        then(postDownloader).should(times(1)).download();
        then(objectMapper).should(times(1)).writeValue(any(File.class),any(Object.class));
    }
}
