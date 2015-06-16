package com.weibohot.crawler.entity;

public class HotNewsDetail {

    public final String introduction;
    public final long readNum;
    public final long discussNum;
    public final long focusNum;

    public HotNewsDetail(final String introduction, final long readNum, final long discussNum,
            final long focusNum) {
        super();
        this.introduction = introduction;
        this.readNum = readNum;
        this.discussNum = discussNum;
        this.focusNum = focusNum;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (this.discussNum ^ (this.discussNum >>> 32));
        result = prime * result + (int) (this.focusNum ^ (this.focusNum >>> 32));
        result = prime * result + ((this.introduction == null) ? 0 : this.introduction.hashCode());
        result = prime * result + (int) (this.readNum ^ (this.readNum >>> 32));
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
        final HotNewsDetail other = (HotNewsDetail) obj;
        if (this.discussNum != other.discussNum)
            return false;
        if (this.focusNum != other.focusNum)
            return false;
        if (this.introduction == null) {
            if (other.introduction != null)
                return false;
        } else if (!this.introduction.equals(other.introduction))
            return false;
        if (this.readNum != other.readNum)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "HotNewsDetail [introduction=" + this.introduction + ", readNum=" + this.readNum
                + ", discussNum=" + this.discussNum + ", focusNum=" + this.focusNum + "]";
    }

}
