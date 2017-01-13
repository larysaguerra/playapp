package asyral.com.playapp.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import asyral.com.playapp.api.Response.Attributes;
import asyral.com.playapp.api.Response.Attributes_;
import asyral.com.playapp.api.Response.Attributes____;
import asyral.com.playapp.api.Response.Attributes_____;
import asyral.com.playapp.api.Response.Attributes______;
import asyral.com.playapp.api.Response.Category;
import asyral.com.playapp.api.Response.Entry;
import asyral.com.playapp.api.Response.Id;
import asyral.com.playapp.api.Response.ImArtist;
import asyral.com.playapp.api.Response.ImImage;
import asyral.com.playapp.api.Response.ImName;
import asyral.com.playapp.api.Response.ImPrice;
import asyral.com.playapp.api.Response.Rights;
import asyral.com.playapp.api.Response.Title;
import asyral.com.playapp.tools.Utils;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


/**
 * Created by guerra on 10/01/17.
 * EntryUtil
 */

public class EntryUtil {

    private static RealmConfiguration configuration(Context context) {
        Realm.init(context);
        return new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    private static void createEntry(final Context context, final Entry entry, final String categoryType) {
        Realm.getInstance(configuration(context)).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                EntryRealmObject entryRealmObject = getEntryRealmObject(context, entry.getId().getAttributes().getImId());
                Utils.logD("createEntry", entry.getImName().getLabel() + " " + categoryType);
                if (entryRealmObject == null) {
                    entryRealmObject = realm.createObject(EntryRealmObject.class);
                }
                entryRealmObject.setCategoryType("" + categoryType);
                entryRealmObject.setId("" + entry.getId().getAttributes().getImId());
                entryRealmObject.setName("" + entry.getImName().getLabel());
                entryRealmObject.setTitle("" + entry.getTitle().getLabel());
                entryRealmObject.setRights("" + entry.getRights().getLabel());
                entryRealmObject.setPriceAmount("" + entry.getImPrice().getAttributes().getAmount());
                entryRealmObject.setPriceCurrency("" + entry.getImPrice().getAttributes().getCurrency());
                entryRealmObject.setArtistLabel("" + entry.getImArtist().getLabel());
                entryRealmObject.setArtistAttributes("" + entry.getImArtist().getAttributes().getHref());
                for (int i = 0; i < entry.getImImage().size(); i++) {
                    ImageRealmObject imageRealmObject = realm.createObject(ImageRealmObject.class);
                    imageRealmObject.setLabel("" + entry.getImImage().get(i).getLabel());
                    imageRealmObject.setHeight("" + entry.getImImage().get(i).getAttributes().getHeight());
                    entryRealmObject.getImageRealmObject().add(imageRealmObject);
                }
                entryRealmObject.setCategoryRealmObject(categoryRealmObject(realm, entry));
                realm.close();
            }
        });
    }

    private static CategoryRealmObject categoryRealmObject(Realm realm, Entry entry) {
        CategoryRealmObject categoryRealmObject = realm.createObject(CategoryRealmObject.class);
        categoryRealmObject.setId(entry.getCategory().getAttributes().getImId());
        categoryRealmObject.setLabel(entry.getCategory().getAttributes().getLabel());
        categoryRealmObject.setTerm(entry.getCategory().getAttributes().getTerm());
        categoryRealmObject.setScheme(entry.getCategory().getAttributes().getScheme());
        return categoryRealmObject;
    }

    public static void createEntrys(Context context, List<Entry> entry, String categoryType) {
        for (int i = 0; i < entry.size(); i++) {
            createEntry(context, entry.get(i), categoryType);
        }
    }

    private static EntryRealmObject getEntryRealmObject(Context context, String id) {
        return Realm.getInstance(configuration(context)).where(EntryRealmObject.class)
                .equalTo("id", id)
                .findFirst();
    }


    public static Entry getEntry(Context context, String id) {
        Utils.logD("getEntry", "getEntry id " + id);
        EntryRealmObject entryRealmObject = getEntryRealmObject(context, id);
        return convertRealObjectToEntry(entryRealmObject);

//        Entry entry = new Entry();
//        String categoryType = entryRealmObject.getCategoryType();
//        entry.setCategoryType(categoryType);
//
//        Id id1 = new Id();
//        Attributes____ attributes____ = new Attributes____();
//        attributes____.setImId(entryRealmObject.getId());
//        id1.setAttributes(attributes____);
//        entry.setId(id1);
//
//        ImName imName = new ImName();
//        imName.setLabel(entryRealmObject.getName());
//        entry.setImName(imName);
//
//        Title title = new Title();
//        title.setLabel(entryRealmObject.getTitle());
//        entry.setTitle(title);
//
//        Rights rights = new Rights();
//        rights.setLabel(entryRealmObject.getRights());
//        entry.setRights(rights);
//
//        ImPrice imPrice = new ImPrice();
//        Attributes_ attributes_ = new Attributes_();
//        attributes_.setAmount(entryRealmObject.getPriceAmount());
//        attributes_.setCurrency(entryRealmObject.getPriceCurrency());
//        imPrice.setAttributes(attributes_);
//        entry.setImPrice(imPrice);
//
//        ImArtist imArtist = new ImArtist();
//        imArtist.setLabel(entryRealmObject.getArtistLabel());
//        Attributes_____ attributes_____ = new Attributes_____();
//        attributes_____.setHref(entryRealmObject.getArtistAttributes());
//        imArtist.setAttributes(attributes_____);
//        entry.setImArtist(imArtist);
//
//        List<ImImage> imImages = new ArrayList<>();
//        for (int i = 0; i < entryRealmObject.getImageRealmObject().size(); i++) {
//            ImImage imImage = new ImImage();
//            imImage.setLabel(entryRealmObject.getImageRealmObject().get(i).getLabel());
//            Attributes attributes = new Attributes();
//            attributes.setHeight(entryRealmObject.getImageRealmObject().get(i).getHeight());
//            imImage.setAttributes(attributes);
//            imImages.add(imImage);
//        }
//        entry.setImImage(imImages);
//
//        Category category = new Category();
//        Attributes______ attributes______ = new Attributes______();
//        attributes______.setImId(entryRealmObject.getCategoryRealmObject().getId());
//        attributes______.setLabel(entryRealmObject.getCategoryRealmObject().getLabel());
//        attributes______.setTerm(entryRealmObject.getCategoryRealmObject().getTerm());
//        attributes______.setScheme(entryRealmObject.getCategoryRealmObject().getScheme());
//        category.setAttributes(attributes______);
//        entry.setCategory(category);
//
//        return entry;
    }

    private static Entry convertRealObjectToEntry(EntryRealmObject entryRealmObject) {
        Entry entry = new Entry();

        String categoryType = entryRealmObject.getCategoryType();
        entry.setCategoryType(categoryType);

        Id id1 = new Id();
        Attributes____ attributes____ = new Attributes____();
        attributes____.setImId(entryRealmObject.getId());
        id1.setAttributes(attributes____);
        entry.setId(id1);

        ImName imName = new ImName();
        imName.setLabel(entryRealmObject.getName());
        entry.setImName(imName);

        Title title = new Title();
        title.setLabel(entryRealmObject.getTitle());
        entry.setTitle(title);

        Rights rights = new Rights();
        rights.setLabel(entryRealmObject.getRights());
        entry.setRights(rights);

        ImPrice imPrice = new ImPrice();
        Attributes_ attributes_ = new Attributes_();
        attributes_.setAmount(entryRealmObject.getPriceAmount());
        attributes_.setCurrency(entryRealmObject.getPriceCurrency());
        imPrice.setAttributes(attributes_);
        entry.setImPrice(imPrice);

        ImArtist imArtist = new ImArtist();
        imArtist.setLabel(entryRealmObject.getArtistLabel());
        Attributes_____ attributes_____ = new Attributes_____();
        attributes_____.setHref(entryRealmObject.getArtistAttributes());
        imArtist.setAttributes(attributes_____);
        entry.setImArtist(imArtist);

        List<ImImage> imImages = new ArrayList<>();
        for (int i = 0; i < entryRealmObject.getImageRealmObject().size(); i++) {
            ImImage imImage = new ImImage();
            imImage.setLabel(entryRealmObject.getImageRealmObject().get(i).getLabel());
            Attributes attributes = new Attributes();
            attributes.setHeight(entryRealmObject.getImageRealmObject().get(i).getHeight());
            imImage.setAttributes(attributes);
            imImages.add(imImage);
        }
        entry.setImImage(imImages);

        Category category = new Category();
        Attributes______ attributes______ = new Attributes______();
        attributes______.setImId(entryRealmObject.getCategoryRealmObject().getId());
        attributes______.setLabel(entryRealmObject.getCategoryRealmObject().getLabel());
        attributes______.setTerm(entryRealmObject.getCategoryRealmObject().getTerm());
        attributes______.setScheme(entryRealmObject.getCategoryRealmObject().getScheme());
        category.setAttributes(attributes______);
        entry.setCategory(category);
        return entry;
    }

    public static ArrayList<Entry> getEntryByCategory(Context context, String categoryType) {
        Utils.logD("getEntry", "getEntryByCategory() " + categoryType);
        ArrayList<EntryRealmObject> entryRealmObjects = getEntryRealmObjectByCategory(context, categoryType);

        ArrayList<Entry> entryArrayList = new ArrayList<>();
        if (!entryRealmObjects.isEmpty()) {
            for (int i = 0; i < entryRealmObjects.size(); i++) {
                entryArrayList.add(convertRealObjectToEntry(entryRealmObjects.get(i)));
            }
        }
        Utils.logD("getEntry", "entryArrayList.size() " + entryArrayList.size());
        return entryArrayList;
    }

    private static ArrayList<EntryRealmObject> getEntryRealmObjectByCategory(final Context context, final String categoryType) {
        final ArrayList<EntryRealmObject> entryRealmObjects = new ArrayList<>();
        Realm.getInstance(configuration(context)).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<EntryRealmObject> models = realm.where(EntryRealmObject.class)
                        .contains("categoryType", categoryType)
                        .findAll();
                Utils.logD("getEntry", "models.size() " + models.size());
                for (int i = 0; i < models.size(); i++) {
                    Utils.logD("getEntry", i + " getName() " + models.get(i).getName());
                    entryRealmObjects.add(models.get(i));
                }

            }
        });
        Utils.logD("getEntry", "entryRealmObjects.size() " + entryRealmObjects.size());
        return entryRealmObjects;
    }

    private static ArrayList<EntryRealmObject> getEntryRealmObjectArray(Context context) {
        ArrayList<EntryRealmObject> entryRealmObjects = new ArrayList<>();
        Realm realm = Realm.getInstance(configuration(context));
        realm.where(EntryRealmObject.class).findAll();
        RealmResults<EntryRealmObject> models = realm.where(EntryRealmObject.class).findAll();
        for (int i = 0; i < realm.where(EntryRealmObject.class).count(); i++) {
            entryRealmObjects.add(models.get(i));
        }
        return entryRealmObjects;
    }


}
