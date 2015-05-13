package model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Administrator on 12.04.2015.
 */
@Entity
@Table(name = "circulation", schema = "", catalog = "library")
public class CirculationEntity {
    private int id;
    private String bookId;
    private int clientId;
    private String givingTime;
    private String receivingTime;
    private byte rentTime;

    private String bookByIdName;
    private String clientByIdName;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "book_id")
    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "client_id")
    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "giving_time")
    public String getGivingTime() {
        return givingTime;
    }
    public void setGivingTime(String givingTime) {
        this.givingTime = givingTime;
    }

    @Basic
    @Column(name = "receiving_time")
    public String getReceivingTime() {
        return receivingTime;
    }
    public void setReceivingTime(String receivingTime) {
        this.receivingTime = receivingTime;
    }

    @Basic
    @Column(name = "rent_time")
    public byte getRentTime() {
        return rentTime;
    }
    public void setRentTime(byte rentTime) {
        this.rentTime = rentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CirculationEntity that = (CirculationEntity) o;

        if (id != that.id) return false;
        if (clientId != that.clientId) return false;
        if (rentTime != that.rentTime) return false;
        if (givingTime != null ? !givingTime.equals(that.givingTime) : that.givingTime != null) return false;
        if (receivingTime != null ? !receivingTime.equals(that.receivingTime) : that.receivingTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) clientId;
        result = 31 * result + (givingTime != null ? givingTime.hashCode() : 0);
        result = 31 * result + (receivingTime != null ? receivingTime.hashCode() : 0);
        result = 31 * result + (int) rentTime;
        return result;
    }

    @Transient
    public String getBookByIdName() {
        return bookByIdName;
    }
    public void setBookByIdName(String bookByIdName) {
        this.bookByIdName = bookByIdName;
    }

    @Transient

    public String getClientByIdName() {
        return clientByIdName;
    }
    public void setClientByIdName(String clientByIdName) {
        this.clientByIdName = clientByIdName;
    }
}
