package com.salim3dd.favorite;

public class List_itme_Index {
    private int id;
    private String Main_Title;
    private int page_id;

    public List_itme_Index(int id, String main_Title, int page_id) {
        this.id = id;
        Main_Title = main_Title;
        this.page_id = page_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain_Title() {
        return Main_Title;
    }

    public void setMain_Title(String main_Title) {
        Main_Title = main_Title;
    }

    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }
}
