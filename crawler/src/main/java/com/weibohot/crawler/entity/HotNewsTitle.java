package com.weibohot.crawler.entity;

public class HotNewsTitle {

    public final String tagName;
    public final String url;
    public final String type;

    public HotNewsTitle(final String tageName, final String url, final String type) {
        this.tagName = tageName;
        this.url = url;
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.tagName == null) ? 0 : this.tagName.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.url == null) ? 0 : this.url.hashCode());
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
        final HotNewsTitle other = (HotNewsTitle) obj;
        if (this.tagName == null) {
            if (other.tagName != null)
                return false;
        } else if (!this.tagName.equals(other.tagName))
            return false;
        if (this.type == null) {
            if (other.type != null)
                return false;
        } else if (!this.type.equals(other.type))
            return false;
        if (this.url == null) {
            if (other.url != null)
                return false;
        } else if (!this.url.equals(other.url))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "HotNewsTitle [tagName=" + this.tagName + ", url=" + this.url + ", type=" + this.type + "]";
    }

}
