package com.zfb.bootworld.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser implements Serializable {
    /**
     * ä¸»é”®ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * ç”¨æˆ·ç¼–ç �
     */
    @Column(name = "user_code")
    private String userCode;

    /**
     * çœŸå®žå§“å��
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * ç™»å½•å��ç§°
     */
    private String username;

    /**
     * å¯†ç �
     */
    private String password;

    /**
     * é‚®ç®±
     */
    private String email;

    /**
     * å¯†ç �çš„ç›�
     */
    private String salt;

    /**
     * 0ã€�æ­£å¸¸;1ã€�ç¦�ç”¨ 
     */
    private Byte disabled;

    /**
     * åˆ›å»ºæ—¶é—´
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取ä¸»é”®ID
     *
     * @return id - ä¸»é”®ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ä¸»é”®ID
     *
     * @param id ä¸»é”®ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取ç”¨æˆ·ç¼–ç �
     *
     * @return user_code - ç”¨æˆ·ç¼–ç �
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置ç”¨æˆ·ç¼–ç �
     *
     * @param userCode ç”¨æˆ·ç¼–ç �
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * 获取çœŸå®žå§“å��
     *
     * @return full_name - çœŸå®žå§“å��
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置çœŸå®žå§“å��
     *
     * @param fullName çœŸå®žå§“å��
     */
    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    /**
     * 获取ç™»å½•å��ç§°
     *
     * @return username - ç™»å½•å��ç§°
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置ç™»å½•å��ç§°
     *
     * @param username ç™»å½•å��ç§°
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取å¯†ç �
     *
     * @return password - å¯†ç �
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置å¯†ç �
     *
     * @param password å¯†ç �
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取é‚®ç®±
     *
     * @return email - é‚®ç®±
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置é‚®ç®±
     *
     * @param email é‚®ç®±
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取å¯†ç �çš„ç›�
     *
     * @return salt - å¯†ç �çš„ç›�
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置å¯†ç �çš„ç›�
     *
     * @param salt å¯†ç �çš„ç›�
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 获取0ã€�æ­£å¸¸;1ã€�ç¦�ç”¨ 
     *
     * @return disabled - 0ã€�æ­£å¸¸;1ã€�ç¦�ç”¨ 
     */
    public Byte getDisabled() {
        return disabled;
    }

    /**
     * 设置0ã€�æ­£å¸¸;1ã€�ç¦�ç”¨ 
     *
     * @param disabled 0ã€�æ­£å¸¸;1ã€�ç¦�ç”¨ 
     */
    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }

    /**
     * 获取åˆ›å»ºæ—¶é—´
     *
     * @return create_time - åˆ›å»ºæ—¶é—´
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置åˆ›å»ºæ—¶é—´
     *
     * @param createTime åˆ›å»ºæ—¶é—´
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userCode=").append(userCode);
        sb.append(", fullName=").append(fullName);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", salt=").append(salt);
        sb.append(", disabled=").append(disabled);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}