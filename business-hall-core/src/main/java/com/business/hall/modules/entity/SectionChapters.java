package com.business.hall.modules.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 章节内容表
 * </p>
 *
 * @author zk
 * @since 2019-03-16
 */
public class SectionChapters implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String href;
    private String chapter;
    private Integer booknameId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public Integer getBooknameId() {
        return booknameId;
    }

    public void setBooknameId(Integer booknameId) {
        this.booknameId = booknameId;
    }

    @Override
    public String toString() {
        return "SectionChapters{" +
        ", id=" + id +
        ", href=" + href +
        ", chapter=" + chapter +
        ", booknameId=" + booknameId +
        "}";
    }
}
