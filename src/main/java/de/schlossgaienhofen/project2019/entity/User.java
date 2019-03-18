
package de.schlossgaienhofen.project2019.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author ahn
 */
@Entity
@Table(name = "Users")
public class User implements Serializable{

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String nickname;
    private String fullName;

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
