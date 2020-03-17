package pl.mw.postdownloader.downloader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import pl.mw.postdownloader.dto.PostDTO;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PostDownloaderTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    PostDownloader postDownloader;


    @BeforeEach
    void setup() {
        String url = "http://www.url";
        ReflectionTestUtils.setField(postDownloader, "apiUrl", url);
    }

    @Test
    void download() {
        PostDTO[] body = new PostDTO[1];
        body[0] = new PostDTO();
        ResponseEntity<PostDTO[]> response = new ResponseEntity<>(body, HttpStatus.OK);
        given(restTemplate.getForEntity(anyString(), eq(PostDTO[].class))).willReturn(response);

        PostDTO[] posts = postDownloader.download();

        then(restTemplate).should(times(1)).getForEntity(anyString(), eq(PostDTO[].class));
        Assertions.assertEquals(1, posts.length);
    }
}
