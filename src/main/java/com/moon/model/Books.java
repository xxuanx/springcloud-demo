package com.moon.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author macbookpro
 */
@Data
@Accessors(chain = true)
public class Books implements Serializable {
    /**
     * 商品id  id
     */
    private Integer id;

    /**
     * 商品名称  name
     */
    private String name;

    /**
     * 商品数量 num
     */
    private Integer num;

    /**
     * books
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商品id 
     * @author macbookpro
     * @date 2020-04-16 16:08:23
     * @return id 商品id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 商品id 
     * @author macbookpro
     * @date 2020-04-16 16:08:23
     * @param id 商品id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 商品名称 
     * @author macbookpro
     * @date 2020-04-16 16:08:23
     * @return name 商品名称 
     */
    public String getName() {
        return name;
    }

    /**
     * 商品名称 
     * @author macbookpro
     * @date 2020-04-16 16:08:23
     * @param name 商品名称 
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 商品数量
     * @author macbookpro
     * @date 2020-04-16 16:08:23
     * @return num 商品数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 商品数量
     * @author macbookpro
     * @date 2020-04-16 16:08:23
     * @param num 商品数量
     */
    public void setNum(Integer num) {
        this.num = num;
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
        sb.append(", num=").append(num);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}