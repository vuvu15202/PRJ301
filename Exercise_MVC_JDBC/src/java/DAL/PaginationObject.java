/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.util.ArrayList;
import java.util.List;
import models.ProductDAO;

/**
 *
 * @author PQT2212
 */
public class PaginationObject<T> {

    private static int numberOfRowEachPage = 10;

    public int getNumberOfRowEachPage() {
        return numberOfRowEachPage;
    }

    public void setNumberOfRowEachPage(int numberOfRowEachPage) {
        PaginationObject.numberOfRowEachPage = numberOfRowEachPage;
    }

    public List<T> getListInCurrentPage(List<T> objects, int page) {
        List<T> list = new ArrayList<>();
        for (int i = ((page - 1) * numberOfRowEachPage); i < (numberOfRowEachPage*page); i++) {
            if(objects.size()==i){
                break;
            }else{
               list.add(objects.get(i)); 
            }           
        }
        return list;
    }

    public int getNumberOfPage(List<T> objects) {
        double total = objects.size();
        return (int) Math.ceil(total / numberOfRowEachPage);
    }
    
    public static void main(String[] args) {
        ArrayList<Product> list = new ProductDAO().getProductbySearch("on");
        PaginationObject paging = new PaginationObject<>();
        List<Product> abc = paging.getListInCurrentPage(list,2);
        for (Product product : abc) {
            System.out.println(product);
        }
    }
}
