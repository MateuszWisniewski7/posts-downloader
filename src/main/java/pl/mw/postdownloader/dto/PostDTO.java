package pl.mw.postdownloader.dto;

import lombok.Data;

@Data
public class PostDTO {
    private int id;
    private String title;
    private String body;
    private int userId;
}
