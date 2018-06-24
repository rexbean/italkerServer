package net.rex.italker.web.push.bean.card;



import com.google.gson.annotations.Expose;
import net.rex.italker.web.push.bean.db.User;
import org.hibernate.annotations.*;


import java.time.LocalDateTime;

/**
 * Created by Rexbean on 6/13/2018.
 */

public class UserCard {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String phone;
    @Expose
    private String portrait;
    @Expose
    private String description;
    @Expose
    private int sex;
    @Expose
    private LocalDateTime modifiedAt = LocalDateTime.now();
    @Expose
    // number of following;
    private int nFollowing;
    @Expose
    // number of followers
    private int nFollower;
    @Expose
    // follow or not
    private boolean isFollow;


    public UserCard(final User user){
        this.id = user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.portrait = user.getPortrait();
        this.description = user.getDescription();
        this.sex = user.getSex();
        this.modifiedAt = user.getUpdateAt();

        //TODO get the follower and followings
        // user.getFollowers.size() will get error
    }

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

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public int getnFollowing() {
        return nFollowing;
    }

    public void setnFollowing(int nFollowing) {
        this.nFollowing = nFollowing;
    }

    public int getnFollower() {
        return nFollower;
    }

    public void setnFollower(int nFollower) {
        this.nFollower = nFollower;
    }

    public boolean getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(boolean isFollow) {
        this.isFollow = isFollow;
    }


}
