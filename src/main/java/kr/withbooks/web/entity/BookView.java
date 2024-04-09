package kr.withbooks.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookView {
    private long id;
    private long categoryId;
    private String title;
    private String author;
    private Date pubDate;
    private String publisher;
    private String isbn13;
    private String description;
    private String purchaseLink;
    private int price;
    private String cover;
    private int publicYn;
    private Date regDate;
    private String categoryName;
}