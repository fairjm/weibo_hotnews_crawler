package com.weibohot.server.entity;

import java.sql.Timestamp;

public class News {

    private String title;
    private long readNum;
    private long discussNum;
    private long focusNum;
    private Timestamp created;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public long getReadNum() {
        return this.readNum;
    }

    public void setReadNum(final long readNum) {
        this.readNum = readNum;
    }

    public long getDiscussNum() {
        return this.discussNum;
    }

    public void setDiscussNum(final long discussNum) {
        this.discussNum = discussNum;
    }

    public long getFocusNum() {
        return this.focusNum;
    }

    public void setFocusNum(final long focusNum) {
        this.focusNum = focusNum;
    }

    public Timestamp getCreated() {
        return this.created;
    }

    public void setCreated(final Timestamp created) {
        this.created = created;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.created == null) ? 0 : this.created.hashCode());
        result = prime * result + (int) (this.discussNum ^ (this.discussNum >>> 32));
        result = prime * result + (int) (this.focusNum ^ (this.focusNum >>> 32));
        result = prime * result + (int) (this.readNum ^ (this.readNum >>> 32));
        result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        final News other = (News) obj;
        if (this.created == null) {
            if (other.created != null)
                return false;
        } else if (!this.created.equals(other.created))
            return false;
        if (this.discussNum != other.discussNum)
            return false;
        if (this.focusNum != other.focusNum)
            return false;
        if (this.readNum != other.readNum)
            return false;
        if (this.title == null) {
            if (other.title != null)
                return false;
        } else if (!this.title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "News [title=" + this.title + ", readNum=" + this.readNum + ", discussNum=" + this.discussNum
                + ", focusNum=" + this.focusNum + ", created=" + this.created + "]";
    }

}
