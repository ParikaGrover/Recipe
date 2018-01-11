package com.example.android.work;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by DELL on 10-01-2018.
 */
@Entity(nameInDb = "Ingredient_content")
public class Ingredient_content {

    @Id(autoincrement = true)
    private Long pid;

    @NotNull
    private Long contactId;

    @Property(nameInDb = "name")
    private String ingredient_name;

    @Generated(hash = 1050166568)
    public Ingredient_content(Long pid, @NotNull Long contactId,
                              String ingredient_name) {
        this.pid = pid;
        this.contactId = contactId;
        this.ingredient_name = ingredient_name;
    }

    @Generated(hash = 13248128)
    public Ingredient_content() {
    }

    public Long getPid() {
        return this.pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getContactId() {
        return this.contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getIngredient_name() {
        return this.ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }


}
