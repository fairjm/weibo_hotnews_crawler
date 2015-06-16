package com.weibohot.server.entity;

public class Category {

    private String categoryName;
    private int newsCount;

    public Category() {

    }

    public Category(final String categoryName, final int newsCount) {
        super();
        this.categoryName = categoryName;
        this.newsCount = newsCount;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(final String categoryName) {
        this.categoryName = categoryName;
    }

    public int getNewsCount() {
        return this.newsCount;
    }

    public void setNewsCount(final int newsCount) {
        this.newsCount = newsCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.categoryName == null) ? 0 : this.categoryName.hashCode());
        result = prime * result + this.newsCount;
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
        final Category other = (Category) obj;
        if (this.categoryName == null) {
            if (other.categoryName != null)
                return false;
        } else if (!this.categoryName.equals(other.categoryName))
            return false;
        if (this.newsCount != other.newsCount)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Category [categoryName=" + this.categoryName + ", newsCount=" + this.newsCount + "]";
    }

}
