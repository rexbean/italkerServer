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
 * Created by Rexbean on 6/13/2018.
 */
@Entity
@Table(name = "TB_USER")
public class User {
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

    @Column(nullable = false, length = 128, unique = true)
    private String name;

    @Column(nullable = false, length = 62, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column
    private String portrait;

    @Column
    private String description;

    @Column(nullable = false)
    private int sex;

    @Column(unique = true)
    // username + phone -> token, must be unique
    private String token;

    @Column
    private String pushId;

    // define the creation timestamp, insert when created

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    // update timestamp
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    // ones I follow
    // correspond to the attr in the TB_USER_FOLLOW.originId
    @JoinColumn(name = "originId")
    // lazy loading, default loading user will not search for this set
    @LazyCollection(LazyCollectionOption.EXTRA)
    // one to many, one user to many follow
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserFollow> following = new HashSet<>();

    // ones follow me
    // correspond to the attr in the TB_USER_FOLLOW.originId
    @JoinColumn(name = "targetId")
    // lazy loading, default loading user will not search for this set
    @LazyCollection(LazyCollectionOption.EXTRA)
    // one to many, one user to many follow
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserFollow> follower = new HashSet<>();


    @JoinColumn(name = "ownerId")
    // lazy loading, default loading user will not search for this set


    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Group> groups = new HashSet<>();






    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
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

    public Set<UserFollow> getFollowing() {
        return following;
    } public void setFollowing(Set<UserFollow> following) {
        this.following = following;
    } public Set<UserFollow> getFollower() {
        return follower;
    } public void setFollower(Set<UserFollow> follower) {
        this.follower = follower;
    } public Set<Group> getGroups() {
        return groups;
    } public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
