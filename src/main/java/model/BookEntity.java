package model;

import javax.persistence.*;

/**
 * Created by Administrator on 12.04.2015.
 */
@Entity
@Table(name = "book", schema = "", catalog = "library")
public class BookEntity {
    private String isbn;
    private String name;
    private int publisherId;
    private int authorId;
    private int price;
    private byte bindingId;
    private int year;
    private int pages;
    private int languageId;
    private byte numberOfBooks;

    @Id
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "publisher_id")
    public int getPublisherId() {
        return publisherId;
    }
    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    @Basic
    @Column(name = "author_id")
    public int getAuthorId() {
        return authorId;
    }
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "binding_id")
    public byte getBindingId() {
        return bindingId;
    }
    public void setBindingId(byte bindingId) {
        this.bindingId = bindingId;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "pages")
    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }

    @Basic
    @Column(name = "language_id")
    public int getLanguageId() {
        return languageId;
    }
    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Basic
    @Column(name = "number_of_books")
    public byte getNumberOfBooks() {
        return numberOfBooks;
    }
    public void setNumberOfBooks(byte numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (publisherId != that.publisherId) return false;
        if (authorId != that.authorId) return false;
        if (price != that.price) return false;
        if (bindingId != that.bindingId) return false;
        if (year != that.year) return false;
        if (pages != that.pages) return false;
        if (languageId != that.languageId) return false;
        if (numberOfBooks != that.numberOfBooks) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = isbn != null ? isbn.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) publisherId;
        result = 31 * result + (int) authorId;
        result = 31 * result + price;
        result = 31 * result + (int) bindingId;
        result = 31 * result + (int) year;
        result = 31 * result + (int) pages;
        result = 31 * result + (int) languageId;
        result = 31 * result + (int) numberOfBooks;
        return result;
    }
}