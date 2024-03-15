package org.taohansen.tvplaylist.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.Objects;

@Entity
@Table(name = "epg")
public class Epg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 20)
    private String name;
    private String comment;
    @NotBlank
    @URL
    private String xmltv;

    public Epg() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getXmltv() {
        return xmltv;
    }

    public void setXmltv(String xmltv) {
        this.xmltv = xmltv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Epg epg = (Epg) o;
        return Objects.equals(id, epg.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
