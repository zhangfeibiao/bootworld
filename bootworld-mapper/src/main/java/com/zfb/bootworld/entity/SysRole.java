package com.zfb.bootworld.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_role")
public class SysRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * è§’è‰²
     */
    private String name;

    private Byte disabled;

    /**
     * æ��è¿°
     */
    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取è§’è‰²
     *
     * @return name - è§’è‰²
     */
    public String getName() {
        return name;
    }

    /**
     * 设置è§’è‰²
     *
     * @param name è§’è‰²
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return disabled
     */
    public Byte getDisabled() {
        return disabled;
    }

    /**
     * @param disabled
     */
    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }

    /**
     * 获取æ��è¿°
     *
     * @return role_code - æ��è¿°
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置æ��è¿°
     *
     * @param roleCode æ��è¿°
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
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
        sb.append(", name=").append(name);
        sb.append(", disabled=").append(disabled);
        sb.append(", roleCode=").append(roleCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}