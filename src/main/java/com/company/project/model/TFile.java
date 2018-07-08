package com.company.project.model;

import javax.persistence.*;

@Table(name = "t_file")
public class TFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;

    /**
     * 地址
     */
    private String name;

    /**
     * 文档的地址
     */
    private String address;

    /**
     * 地址
     */
    private String haxi;

    /**
     * 地址
     */
    @Column(name = "create_time")
    private String createTime;

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
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取地址
     *
     * @return name - 地址
     */
    public String getName() {
        return name;
    }

    /**
     * 设置地址
     *
     * @param name 地址
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取文档的地址
     *
     * @return address - 文档的地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置文档的地址
     *
     * @param address 文档的地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取地址
     *
     * @return haxi - 地址
     */
    public String getHaxi() {
        return haxi;
    }

    /**
     * 设置地址
     *
     * @param haxi 地址
     */
    public void setHaxi(String haxi) {
        this.haxi = haxi;
    }

    /**
     * 获取地址
     *
     * @return create_time - 地址
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置地址
     *
     * @param createTime 地址
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}