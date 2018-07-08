package com.company.project.model;

import javax.persistence.*;

@Table(name = "t_file_images")
public class TFileImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 转换后id
     */
    @Column(name = "word_id")
    private Integer wordId;

    /**
     * 文档的地址
     */
    private String name;

    /**
     * 图片地址
     */
    private String address;

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
     * 获取转换后id
     *
     * @return word_id - 转换后id
     */
    public Integer getWordId() {
        return wordId;
    }

    /**
     * 设置转换后id
     *
     * @param wordId 转换后id
     */
    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    /**
     * 获取文档的地址
     *
     * @return name - 文档的地址
     */
    public String getName() {
        return name;
    }

    /**
     * 设置文档的地址
     *
     * @param name 文档的地址
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取图片地址
     *
     * @return address - 图片地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置图片地址
     *
     * @param address 图片地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 构造器 传入一个地址
     * @param address
     */
    public TFileImages(String address) {
        super();
        this.address = address;
    }

    public TFileImages() {
    }

    /*public TFileImages(Integer id, Integer wordId, String name, String address) {
        this.id=id;
        this.wordId = wordId;
        this.name = name;
        this.address = address;
    }*/
}