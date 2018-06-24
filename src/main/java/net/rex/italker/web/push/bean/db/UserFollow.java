package net.rex.italker.web.push.bean.db;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rexbean on 6/20/2018.
 */

/***
 * model of the user relationship, middle table between users
 */
@Entity
@Table(name = "TB_USER_FOLLOW")
public class UserFollow {

    @Id
    @PrimaryKeyJoinColumn
    // the primary key 's type is uuid
    @GeneratedValue(generator = "uuid")
    // make the uuid generator to uuid2, uuid2 is the uuid to string normally
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    // cannot make change, cannot be null
    @Column(updatable=false, nullable=false)
    // auto increased id won't be safe by web spider
    private String id;

    // many to one, one can follow many user
    // can create many follow info
    // many to one: user to user follow
    // optional cannot be optional, must have origin
    @ManyToOne(optional = false)
    // define the originId as the id
    @JoinColumn(name = "originId")
    // User who makes the request
    private User origin;
    // to search efficiently
    @Column(nullable = false, updatable = false, insertable = false)
    private String originId;


    // many to one, user can be followed,
    @ManyToOne(optional = false)
    @JoinColumn(name = "targetId")
    // User who get the request
    private User target;
    @Column(nullable = false, updatable = false, insertable = false)
    private String targetId;

    // alias,
    @Column
    private String alias;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    // update timestamp
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();






    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOrigin() {
        return origin;
    }

    public void setOrigin(User origin) {
        this.origin = origin;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
