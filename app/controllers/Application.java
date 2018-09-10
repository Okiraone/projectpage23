package controllers;

import models.Product;
import models.Prog;
import models.Pubg;
import models.c;
import play.Play;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import views.html.*;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Application extends Controller {



    public static Result main(Html content) {
        return ok(main.render(content));
    }
    public static Result index(){
        return main(home_page.render());
    }
    public static Result Pro(){
        return main(Products .render());
    }
    public static Result Promo(){
        return main(Promotions.render());
    }

    public static Pubg pubg;
    public static Form<Pubg> pubgForm = Form.form(Pubg.class);
    public static List<Pubg> pubgs = new ArrayList<Pubg>();

    public static Form<Prog>progForm = Form.form(Prog.class);
    public static List<Prog>progList = new ArrayList<Prog>();
    public static Prog prog;
    public static String pubgPicPaht = Play.application().configuration().getString("pubg_pic_path");

    public static List<c>cList = new ArrayList<c>();
    public static  Form<c>cForm = Form.form(c.class);
    public static  c cs;

    public static Result Showpro(){
        String myname;
        Product pro1;
        pro1=new Product();
        pro1 .setId("ER001");
        pro1.setName("ปืนM416");
        pro1.setType("ปืน");
        pro1.setSize("เล็ก");
        pro1.setPrice(300);
        pro1.setAmount(20);
Product pro2 = new Product("ER002","ปืนAKM","ปืน","เล็ก",300,20);
Product pro3 = new Product("ER003","ปืนKRISS Vector","ปืน","เล็ก",300,20);
Product pro4 = new Product("DR004","กระเป๋าLevel 3","กระเป๋า","ใหญ่",1390,30);
Product pro5 = new Product("DR005","กระเป๋าLevel 3 ดำ","กระเป๋า","ใหญ่",1390,25);
Product pro6 = new Product("DR006","กระเป๋าLevel 3 น้ำตาล","กระเป๋า","ใหญ่",1390,25);
return main(Showpro.render(pro1,pro2,pro3,pro4,pro5,pro6));
    }

    public static Result tet() { return ok(test.render());
    }
public static Result inputPro(){
    return main(inputPro.render());
}
public static Result postPro(){
    DynamicForm myForm = Form.form().bindFromRequest();
    String id,name,type,size;
    double amount,price;
    id = myForm.get("id");
    name = myForm.get("name");
    type =  myForm.get("type");
    size =  myForm.get("size");
    price = Double.parseDouble(myForm.get("price"));
    amount = Double.parseDouble(myForm.get("amount"));

    pubg = new Pubg(id, name, type, size, price, amount );

    return main(Showpro2.render(pubg));
}
    public static Result pubg_form_helper(){
        pubgForm = Form.form(Pubg.class);

        return main(pubg_helper.render(pubgForm));

    }

    public static Result pubg_post_helper(){
        Form<Pubg> newForm = pubgForm.bindFromRequest();
        if(newForm.hasErrors()){
            return main(pubg_helper.render(newForm));
        }
        else {
            pubg = newForm.get();
            return main(Showpro2.render(pubg));
        }

    }

    public  static  Result pubg_helper_list(){
        pubgForm = Form.form(Pubg.class);

        return main(pubg_helper_list.render(pubgForm));
    }

    public static Result pubg_post_helper_list(){
        Form<Pubg> newForm = pubgForm.bindFromRequest();
        if(newForm.hasErrors()){
            return main(pubg_helper_list.render(newForm));
        }
        else {
            pubg = newForm.get();
            pubgs.add(pubg);

            return main(Showpro2_list.render(pubgs));
        }

    }

    public static Result Prog_helper_list(){

        progForm = Form.form(Prog.class);
        return main(Prog_helper_list.render(progForm));

    }

    public static Result prog_post_helper_list(){

        Form<Prog> myForm = progForm.bindFromRequest();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart pic = body.getFile("pic");
        String fileName,contentType;
        if (myForm.hasErrors()){
            return main(Prog_helper_list.render(myForm));

        }
        else {
            if (pic != null) {
                contentType = pic.getContentType();
                File file = pic.getFile();
                fileName = pic.getFilename();
                if (!contentType.startsWith("image")) {
                    return main(Prog_helper_list.render(myForm));
                }
                prog = myForm.get();
                fileName = prog.getIdpro() + fileName.substring(fileName.lastIndexOf("."));
                file.renameTo(new File(pubgPicPaht, fileName));
                prog.setPic(fileName);
                progList.add(prog);
            }
            return main(prog_show_list.render(progList));
        }

    }
    public static Result showProList(){
        return main(prog_show_list.render(progList));
    }

    public  static Result showOne (String idpro){
        for(int i = 0; i<progList.size(); i++) {
            if (progList.get(i).getIdpro().equals(idpro)) {
                prog = progList.get(i);
                break;
            }
        }
        return main(showOne_list.render(prog));
    }

    public static Result showCList(){
        cList = c.list();
        return main(showCList.render(cList));
    }

    public static  Result C_add_form(){
        cForm = Form.form(c.class);
        return main(C_add_form.render(cForm));
        }

    public static Result save(){
        Form<c> cForm = Form.form(c.class).bindFromRequest();
        if(cForm.hasErrors()){
            flash("danger","Please Correct the Form Below");
            return badRequest(C_add_form.render(cForm));
        }
        c cs = cForm.get();
        cs.save();
        flash("success","Product Save Successfully");
        return redirect("/showCList");
    }

    public static Result edit(String idpro ) {

        c cs = c.finder.byId(idpro);
        if (cs == null) {
            return ok ();
        }
        else {
            Form<c> cForm = Form.form(c.class).fill(cs);
            return main(edit_show. render(cForm));
        }
    }

    public static Result update(){

        Form<c> cForm = Form.form(c.class).bindFromRequest();
        if (cForm.hasErrors()) {
            return main(edit_show. render(cForm));
        }
        else {
            cs = cForm. get ();
            cs.update ();
            return main(showCList.render(cList));
        }
    }

    public static Result delete(String idpro) {
        c cs = c.finder.byId(idpro);
        if (cs!= null) {
            cs.delete ();
        }
        return main(showCList.render(cList));
    }



}