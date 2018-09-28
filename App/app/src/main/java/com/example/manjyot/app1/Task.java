package com.example.manjyot.app1;

import java.util.Date;

/**
 * Created by manjyot on 29/01/2018.
 */

public class Task {
    private String tTitle;
    private String tPriority;
    private String tDate;

    public String gettTitle() {
        return tTitle;
    }

    public void settTitle(String tTitle) {
        this.tTitle = tTitle;
    }

    public String gettPriority() {
        return tPriority;
    }

    public void settPriority(String tPriority) {
        this.tPriority = tPriority;
    }

    public String gettDate() {
        return tDate;
    }

    public void settDate(String tDate) {
        this.tDate = tDate;
    }
}
