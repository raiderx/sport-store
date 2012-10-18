package org.karpukhin.sportstore.core.model;

import java.math.BigDecimal;

/**
 * @author Pavel Karpukhin
 * @since 18.10.12
 */
public class SkiBoot extends Entity {

    private String brand;
    private String name;
    private String article;
    private Integer flexIndex;
    private BigDecimal minSize;
    private BigDecimal maxSize;
    private String description;
    private String color;
    private BigDecimal price;

    /**
     * Default constructor
     */
    public SkiBoot() {
    }

    public SkiBoot(String brand, String name, String article, Integer flexIndex, BigDecimal minSize, BigDecimal maxSize, String description, String color, BigDecimal price) {
        this.brand = brand;
        this.name = name;
        this.article = article;
        this.flexIndex = flexIndex;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.description = description;
        this.color = color;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Integer getFlexIndex() {
        return flexIndex;
    }

    public void setFlexIndex(Integer flexIndex) {
        this.flexIndex = flexIndex;
    }

    public BigDecimal getMinSize() {
        return minSize;
    }

    public void setMinSize(BigDecimal minSize) {
        this.minSize = minSize;
    }

    public BigDecimal getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(BigDecimal maxSize) {
        this.maxSize = maxSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
