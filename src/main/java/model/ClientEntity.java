package model;

import javax.persistence.*;

/**
 * Created by Administrator on 12.04.2015.
 */
@Entity
@Table(name = "client", schema = "", catalog = "library")
public class ClientEntity {
    private int id;
    private String name;
    private int birth;
    private byte banned;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    @Column(name = "birth")
    public int getBirth() {
        return birth;
    }
    public void setBirth(int birth) {
        this.birth = birth;
    }

    @Basic
    @Column(name = "banned")
    public byte getBanned() {
        return banned;
    }
    public void setBanned(byte banned) {
        this.banned = banned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEntity that = (ClientEntity) o;

        if (id != that.id) return false;
        if (birth != that.birth) return false;
        if (banned != that.banned) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) birth;
        result = 31 * result + (int) banned;
        return result;
    }
}
