package com.example.admin.appclean.utils.PermissionUtil;

public class PermisionItem {

    private String name;
    private boolean isGranted;


    public PermisionItem(String name, boolean isGranted) {
        this.name = name;
        this.isGranted = isGranted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGranted() {
        return isGranted;
    }

    public void setGranted(boolean granted) {
        isGranted = granted;
    }
}
