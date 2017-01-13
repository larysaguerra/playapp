package asyral.com.playapp.data;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by guerra on 10/01/17.
 * EntryRealmObject
 */

public class EntryRealmObject extends RealmObject {

    private String categoryType;
    private String id;
    private String name;
    private String title;
    private String rights;
    private String priceAmount;
    private String priceCurrency;
    private String artistLabel;
    private String artistAttributes;

    private RealmList<ImageRealmObject> imageRealmObject = new RealmList<>();
    private CategoryRealmObject categoryRealmObject;

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(String priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public String getArtistLabel() {
        return artistLabel;
    }

    public void setArtistLabel(String artistLabel) {
        this.artistLabel = artistLabel;
    }

    public String getArtistAttributes() {
        return artistAttributes;
    }

    public void setArtistAttributes(String artistAttributes) {
        this.artistAttributes = artistAttributes;
    }

    public RealmList<ImageRealmObject> getImageRealmObject() {
        return imageRealmObject;
    }

    public void setImageRealmObject(RealmList<ImageRealmObject> imageRealmObject) {
        this.imageRealmObject = imageRealmObject;
    }

    public CategoryRealmObject getCategoryRealmObject() {
        return categoryRealmObject;
    }

    public void setCategoryRealmObject(CategoryRealmObject categoryRealmObject) {
        this.categoryRealmObject = categoryRealmObject;
    }
}
