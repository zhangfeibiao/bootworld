package com.zfb.bootworld.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_permission")
public class SysPermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * çˆ¶è�œå�•IDï¼Œä¸€çº§è�œå�•ä¸º0
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * è�œå�•å��ç§°
     */
    private String name;

    /**
     * è�œå�•URL
     */
    private String url;

    /**
     * æŽˆæ�ƒ(å¤šä¸ªç”¨é€—å�·åˆ†éš”ï¼Œå¦‚ï¼šuser:list,user:create)
     */
    private String perms;

    /**
     * ç±»åž‹   0ï¼šç›®å½•   1ï¼šè�œå�•   2ï¼šæŒ‰é’®
     */
    private Integer type;

    /**
     * è�œå�•å›¾æ ‡
     */
    private String icon;

    /**
     * æŽ’åº�
     */
    @Column(name = "order_num")
    private Integer orderNum;

    private String method;

    /**
     * æ�ƒé™�ç±»åž‹ 1ï¼šè�œå�• 2 èµ„æº�
     */
    @Column(name = "permission_type")
    private Integer permissionType;

    /**
     * æŽ’åº�
     */
    private String sort;

    /**
     * è�œå�•å��ç§°
     */
    private String description;

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
     * 获取çˆ¶è�œå�•IDï¼Œä¸€çº§è�œå�•ä¸º0
     *
     * @return parent_id - çˆ¶è�œå�•IDï¼Œä¸€çº§è�œå�•ä¸º0
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置çˆ¶è�œå�•IDï¼Œä¸€çº§è�œå�•ä¸º0
     *
     * @param parentId çˆ¶è�œå�•IDï¼Œä¸€çº§è�œå�•ä¸º0
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取è�œå�•å��ç§°
     *
     * @return name - è�œå�•å��ç§°
     */
    public String getName() {
        return name;
    }

    /**
     * 设置è�œå�•å��ç§°
     *
     * @param name è�œå�•å��ç§°
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取è�œå�•URL
     *
     * @return url - è�œå�•URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置è�œå�•URL
     *
     * @param url è�œå�•URL
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取æŽˆæ�ƒ(å¤šä¸ªç”¨é€—å�·åˆ†éš”ï¼Œå¦‚ï¼šuser:list,user:create)
     *
     * @return perms - æŽˆæ�ƒ(å¤šä¸ªç”¨é€—å�·åˆ†éš”ï¼Œå¦‚ï¼šuser:list,user:create)
     */
    public String getPerms() {
        return perms;
    }

    /**
     * 设置æŽˆæ�ƒ(å¤šä¸ªç”¨é€—å�·åˆ†éš”ï¼Œå¦‚ï¼šuser:list,user:create)
     *
     * @param perms æŽˆæ�ƒ(å¤šä¸ªç”¨é€—å�·åˆ†éš”ï¼Œå¦‚ï¼šuser:list,user:create)
     */
    public void setPerms(String perms) {
        this.perms = perms == null ? null : perms.trim();
    }

    /**
     * 获取ç±»åž‹   0ï¼šç›®å½•   1ï¼šè�œå�•   2ï¼šæŒ‰é’®
     *
     * @return type - ç±»åž‹   0ï¼šç›®å½•   1ï¼šè�œå�•   2ï¼šæŒ‰é’®
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置ç±»åž‹   0ï¼šç›®å½•   1ï¼šè�œå�•   2ï¼šæŒ‰é’®
     *
     * @param type ç±»åž‹   0ï¼šç›®å½•   1ï¼šè�œå�•   2ï¼šæŒ‰é’®
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取è�œå�•å›¾æ ‡
     *
     * @return icon - è�œå�•å›¾æ ‡
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置è�œå�•å›¾æ ‡
     *
     * @param icon è�œå�•å›¾æ ‡
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取æŽ’åº�
     *
     * @return order_num - æŽ’åº�
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置æŽ’åº�
     *
     * @param orderNum æŽ’åº�
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * @return method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * 获取æ�ƒé™�ç±»åž‹ 1ï¼šè�œå�• 2 èµ„æº�
     *
     * @return permission_type - æ�ƒé™�ç±»åž‹ 1ï¼šè�œå�• 2 èµ„æº�
     */
    public Integer getPermissionType() {
        return permissionType;
    }

    /**
     * 设置æ�ƒé™�ç±»åž‹ 1ï¼šè�œå�• 2 èµ„æº�
     *
     * @param permissionType æ�ƒé™�ç±»åž‹ 1ï¼šè�œå�• 2 èµ„æº�
     */
    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    /**
     * 获取æŽ’åº�
     *
     * @return sort - æŽ’åº�
     */
    public String getSort() {
        return sort;
    }

    /**
     * 设置æŽ’åº�
     *
     * @param sort æŽ’åº�
     */
    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    /**
     * 获取è�œå�•å��ç§°
     *
     * @return description - è�œå�•å��ç§°
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置è�œå�•å��ç§°
     *
     * @param description è�œå�•å��ç§°
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", url=").append(url);
        sb.append(", perms=").append(perms);
        sb.append(", type=").append(type);
        sb.append(", icon=").append(icon);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", method=").append(method);
        sb.append(", permissionType=").append(permissionType);
        sb.append(", sort=").append(sort);
        sb.append(", description=").append(description);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}