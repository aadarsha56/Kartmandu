package com.example.kartmandu.Model;

public class ItemModel {
    private String prodimagename;
    private String prodname;
    private String prodtype;
    private String prodprice;

    public String getProdimagename() {
        return prodimagename;
    }

    public void setProdimagename(String prodimagename) {
        this.prodimagename = prodimagename;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getProdtype() {
        return prodtype;
    }

    public void setProdtype(String prodtype) {
        this.prodtype = prodtype;
    }

    public String getProdprice() {
        return prodprice;
    }

    public void setProdprice(String prodprice) {
        this.prodprice = prodprice;
    }

    public ItemModel(String prodimagename, String prodname, String prodtype, String prodprice) {
        this.prodimagename = prodimagename;
        this.prodname = prodname;
        this.prodtype = prodtype;
        this.prodprice = prodprice;
    }
}
