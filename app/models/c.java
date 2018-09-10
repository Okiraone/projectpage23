package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Random;

/**
 * Created by KKT on 28/8/2561.
 */
@Entity
@Table (name="tbprog")
public class c extends  Model{
    @Id
    private String idpro,name, type, size ,color,detail,pattern;
    private double price, amount, sale, netprice;
    private String pic;

    public c() {
        setIdpro();
    }

    public c(String name, String type, String size, String color, String detail, String pattern, double price, double amount, double sale, double netprice, String pic) {
        setIdpro();
        this.name = name;
        this.type = type;
        this.size = size;
        this.color = color;
        this.detail = detail;
        this.pattern = pattern;
        this.price = price;
        this.amount = amount;
        this.sale = sale;
        this.netprice = netprice;
        this.pic = pic;
    }
    public String getIdpro() {
        return idpro;
    }

    private void setIdpro() {
        Random random = new Random();
        idpro = Integer.toString(random.nextInt(100000)+1);

    }
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public String getSize() {return size;}

    public void setSize(String size) {this.size = size;}

    public String getColor() {return color;}

    public void setColor(String color) {this.color = color;}

    public String getDetail() {return detail;}

    public void setDetail(String detail) {this.detail = detail;}

    public String getPattern() {return pattern;}

    public void setPattern(String pattern) {this.pattern = pattern;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}

    public double getAmount() {return amount;}

    public void setAmount(double amount) {this.amount = amount;}

    public double getSale() {return sale;}

    public void setSale(double sale) {this.sale = sale;}

    public String getPic() {return pic;}

    public void setPic(String pic) {this.pic = pic;}

    public double getNetprice() {

        return netprice = getPrice() - (getPrice() * sale / 100);
    }
    public static Finder<String, c>finder = new
            Finder<String, c>(String.class,c.class);

     public static void create(c cs){
        cs.save();
    }
    public static void update(c cs){
        cs.update();
    }
    public static void delete(c cs){
        cs.delete();
    }
    public static List<c> list(){
        return finder.all();
    }
}
