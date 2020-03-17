package pl.mw.postdownloader.exception;

public class PostsDownloadingException extends RuntimeException {
    public PostsDownloadingException() {
        super("Could not download posts.");
    }
}
