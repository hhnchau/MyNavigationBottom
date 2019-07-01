package info.kingpes.mynavigationbottom;

public class NavItem {
    private int iconActive;
    private int iconInactive;
    private boolean active;

    public NavItem(int iconActive, int iconInactive, boolean active) {
        this.iconActive = iconActive;
        this.iconInactive = iconInactive;
        this.active = active;
    }

    public NavItem(int iconActive, int iconInactive) {
        this.iconActive = iconActive;
        this.iconInactive = iconInactive;
    }

    public int getIconActive() {
        return iconActive;
    }

    public int getIconInactive() {
        return iconInactive;
    }

    public boolean isActive() {
        return active;
    }
}
