package me.gustavs.piggybank.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "opration")
public class Operation {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "amount")
    private float amount;

    @Property(nameInDb = "description")
    private String description;

    @Property(nameInDb = "created_at")
    private long createdAt;

    @Generated(hash = 1926347995)
    public Operation(Long id, float amount, String description, long createdAt) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
    }

    @Generated(hash = 1326595030)
    public Operation() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

}