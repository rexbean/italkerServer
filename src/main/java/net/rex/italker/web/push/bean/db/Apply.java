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
@Table(name = "TB_APPLY")
public class Apply {
    public static final int APPLY_TYPE_ADD_USER = 1;
    public static final int APPLY_TYPE_JOIN_GROUP = 2;

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

    @Column(nullable = false)
    private String description;


    // the type can be pic, file, audio
    @Column(columnDefinition = "TEXT")
    private String attachment;

    @Column(nullable = false)
    private String applyType;

    // type: APPLY_TYPE_ADD_USER userId, APPLY_TYPE_JOIN_GROUP groupId
    // don't use strong connection
    @Column(nullable = false)
    private String targetId;

    // can be null which is system message
    @ManyToOne()
    @JoinColumn(name = "applicantId")
    private User applicant;
    @Column(updatable = false, insertable = false)
    private String applicantId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
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
