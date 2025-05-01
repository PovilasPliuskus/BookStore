package models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookModel {

    private Integer id;
    private String title;
    private Integer pageCount;
    private Integer version;
    private boolean forceUpdate;

    public BookModel() {

    }

    public BookModel(Integer id, String title, Integer pageCount, Integer version) {
        this.id = id;
        this.title = title;
        this.pageCount = pageCount;
        this.version = version;
    }
}
