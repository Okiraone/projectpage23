package models;

import play.db.ebean.Model;

import java.util.Random;

/**
 * Created by KKT on 5/8/2561.
 */
public class Prog extends Pubg {

    private String idpro,color,detail,pattern;
    private double sale;
    private String pic;

    public Prog() {

        setIdpro();
    }

    public Prog(String id, String name, String type, String size, String color, String detail, String pattern, double price, double amount, double sale, String pic) {

        super(id, name, type, size, price, amount);
        setIdpro();
        this.color = color;
        this.detail = detail;
        this.pattern = pattern;
        this.sale = sale;
        this.pic = pic;
    }

    public String getIdpro() {
        return idpro;
    }

    private void setIdpro() {
        Random random = new Random();
        idpro = Integer.toString(random.nextInt(100000)+1);

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public double getNetPrice(){

        return getPrice() - (getPrice()* sale/100);
    }



}
