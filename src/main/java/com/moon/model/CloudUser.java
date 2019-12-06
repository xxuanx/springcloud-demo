package com.moon.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class CloudUser implements Serializable {
    /**
     * 主键 id
     */
    private Integer id;

    /**
     * 姓名 name
     */
    private String name;

    /**
     * 性别 gender
     */
    private Integer gender;

    /**
     * 创建时间 create_time
     */
    private Date createTime;

    /**
     * 更新时间 update_time
     */
    private Date updateTime;

    /**
     * cloud_user
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * @author macbookpro
     * @date 2019-12-07 05:00:01
     * @return id 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     * @author macbookpro
     * @date 2019-12-07 05:00:01
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 姓名
     * @author macbookpro
     * @date 2019-12-07 05:00:01
     * @return name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @author macbookpro
     * @date 2019-12-07 05:00:01
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 性别
     * @author macbookpro
     * @date 2019-12-07 05:00:01
     * @return gender 性别
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 性别
     * @author macbookpro
     * @date 2019-12-07 05:00:01
     * @param gender 性别
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 创建时间
     * @author macbookpro
     * @date 2019-12-07 05:00:01
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @author macbookpro
     * @date 2019-12-07 05:00:01
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     * @author macbookpro
     * @date 2019-12-07 05:00:01
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @author macbookpro
     * @date 2019-12-07 05:00:01
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", gender=").append(gender);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}