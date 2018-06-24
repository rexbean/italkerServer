package net.rex.italker.web.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Rexbean on 6/20/2018.
 */
@Entity
@Table(name = "TB_GROUP_MEMBER")
public class GroupMember {
    public static final int NOTIFY_LEVEL_INVALID = -1; // won't receiving
    public static final int NOTIFY_LEVEL_NONE = 0; // default
    public static final int NOTIFY_LEVEL_CLOSE = 1; // receiving but not notify

    public static final int PERMISSION_TYPE_NONE = 0; // default
    public static final int PERMISSION_TYPE_ADMIN = 1; // administrator
    public static final int PERMISSION_TYPE_ADMIN_SU = 100; // owner


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

    @Column
    private String alias;

    // notification level
    @Column(nullable = false)
    private int notifyLevel = NOTIFY_LEVEL_NONE;

    // member permission type
    @Column(nullable = false)
    private int permissionType = PERMISSION_TYPE_NONE;


    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    // update timestamp
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();


    @JoinColumn(name = "memberId")
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User member;
    @Column(nullable = false, insertable = false, updatable = false)
    private String memberId;


    @JoinColumn(name = "groupId")
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Group group;
    @Column(nullable = false, insertable = false, updatable = false)
    private String groupId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getNotifyLevel() {
        return notifyLevel;
    }

    public void setNotifyLevel(int notifyLevel) {
        this.notifyLevel = notifyLevel;
    }

    public int getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(int permissionType) {
        this.permissionType = permissionType;
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
