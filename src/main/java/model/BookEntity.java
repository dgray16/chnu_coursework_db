package model;

import javax.persistence.*;
import java.sql.Date;

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
    private double price;
    private int bindingId;
    private int year;
    private int pages;
    private int languageId;
    private byte numberOfBooks;
    private String incomeDate;

    private PublisherEntity publisherById;
    private AuthorEntity authorById;
    private BindingEntity bindingById;
    private LanguageEntity languageById;

    private String publisherByIdName;
    private String authorByIdFullName;
    private String bindingByIdType;
    private String languageByIdName;

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
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "binding_id")
    public int getBindingId() {
        return bindingId;
    }
    public void setBindingId(int bindingId) {
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

    @Basic
    @Column(name = "income_date")
    public String getIncomeDate() {
        return incomeDate;
    }
    public void setIncomeDate(String incomeDate) {
        this.incomeDate = incomeDate;
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
        result = 31 * result + (int) bindingId;
        result = 31 * result + (int) year;
        result = 31 * result + (int) pages;
        result = 31 * result + (int) languageId;
        result = 31 * result + (int) numberOfBooks;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id", nullable = true, insertable = false, updatable = false)
    public PublisherEntity getPublisherById(){
        return  publisherById;
    }
    public void setPublisherById(PublisherEntity publisherById){
        this.publisherById = publisherById;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = true, insertable = false, updatable = false)
    public AuthorEntity getAuthorById() {
        return authorById;
    }
    public void setAuthorById(AuthorEntity authorById) {
        this.authorById = authorById;
    }

    @ManyToOne
    @JoinColumn(name = "binding_id", referencedColumnName = "id", nullable = true, insertable = false, updatable = false)
    public BindingEntity getBindingById() {
        return bindingById;
    }
    public void setBindingById(BindingEntity bindingById) {
        this.bindingById = bindingById;
    }

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id", nullable = true, insertable = false, updatable = false)
    public LanguageEntity getLanguageById() {
        return languageById;
    }
    public void setLanguageById(LanguageEntity languageById) {
        this.languageById = languageById;
    }

    @Transient
    public String getPublisherByIdName() {
        return publisherByIdName;
    }
    public void setPublisherByIdName(String publisherByIdName) {
        this.publisherByIdName = publisherByIdName;
    }

    @Transient
    public String getAuthorByIdFullName() {
        return authorByIdFullName;
    }
    public void setAuthorByIdFullName(String authorByIdFullName) {
        this.authorByIdFullName = authorByIdFullName;
    }

    @Transient
    public String getBindingByIdType() {
        return bindingByIdType;
    }
    public void setBindingByIdType(String bindingByIdType) {
        this.bindingByIdType = bindingByIdType;
    }

    @Transient
    public String getLanguageByIdName() {
        return languageByIdName;
    }
    public void setLanguageByIdName(String languageByIdName) {
        this.languageByIdName = languageByIdName;
    }
}
