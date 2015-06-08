package com.weibohot.crawler;

public class HotNews {

    public final String tagName;
    public final String url;

    public HotNews(final String tageName, final String url) {
        this.tagName = tageName;
        this.url = url;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.tagName == null) ? 0 : this.tagName.hashCode());
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
        final HotNews other = (HotNews) obj;
        if (this.tagName == null) {
            if (other.tagName != null)
                return false;
        } else if (!this.tagName.equals(other.tagName))
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
        return "HotNews [tagName=" + this.tagName + ", url=" + this.url + "]";
    }

}
