package org.karpukhin.sportstore.core.model;

import java.math.BigDecimal;

/**
 * @author Pavel Karpukhin
 * @since 12.10.12
 */
public class Ski extends Entity {

    private String brand;
    private String name;
    private String article;
    private String description;
    private BigDecimal price;

    public Ski() {
    }

    public Ski(String brand, String name, String article, String description, BigDecimal price) {
        this.brand = brand;
        this.name = name;
        this.article = article;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
