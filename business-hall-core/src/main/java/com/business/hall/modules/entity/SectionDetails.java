package com.business.hall.modules.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 章节详情表
 * </p>
 *
 * @author zk
 * @since 2019-03-16
 */
public class SectionDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String chapter;
    private String content;
    private String prev;
    private String next;
    /**
     * 小说章节ID
     */
    private Integer sectionChapterId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Integer getSectionChapterId() {
        return sectionChapterId;
    }

    public void setSectionChapterId(Integer sectionChapterId) {
        this.sectionChapterId = sectionChapterId;
    }

    @Override
    public String toString() {
        return "SectionDetails{" +
        ", id=" + id +
        ", chapter=" + chapter +
        ", content=" + content +
        ", prev=" + prev +
        ", next=" + next +
        ", sectionChapterId=" + sectionChapterId +
        "}";
    }
}
