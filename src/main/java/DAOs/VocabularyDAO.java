package main.java.DAOs;

import javax.persistence.*;

/**
 * Created by Tatka on 28.11.2016.
 */
@Entity
@Table(name = "vocabulary")
public class VocabularyDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String type;
    @Column
    private String value;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public VocabularyDAO() {
    }

    public VocabularyDAO(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
