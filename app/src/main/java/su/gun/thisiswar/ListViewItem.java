package su.gun.thisiswar;

import android.graphics.drawable.Drawable;

/**
 * Created by hs on 2016-11-18.
 */

public class ListViewItem {

    private Drawable icon;
    private String name;
    private String email;

    public ListViewItem(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public ListViewItem() {

    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
    public Drawable getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }
}
