/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.util.ArrayList;
import models.ProductDAO;

/**
 *
 * @author user
 */
public class Cart {
    private ArrayList<Item> cart;

    public Cart() {
        this.cart = new ArrayList<>();
    }

    public ArrayList<Item> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Item> cart) {
        this.cart = cart;
    }
    
    public int numberOfItem(){
        return cart.size();
    }
    
    public boolean isProductInCart(int id){
        for (Item item : cart) {
            if(item.getProduct().getProductID()==id){
                return true;
            }
        }
        return false;
    }
    
    public int getQuantityByID(int id){
        for (Item item : cart) {
            if(item.getProduct().getProductID()==id){
                return item.getQuantity();
            }
        }
        return 0;
    }
    
   
    public ArrayList<Item> decryptionCookiesText(ArrayList<String> cookiesText){
        for (String string : cookiesText) {
            String[] arr=string.split("-");
            Product product = new ProductDAO().getProductInfor(Integer.parseInt(arr[0]));
            int quantity=Integer.parseInt(arr[1]);
            cart.add(new Item(product, quantity));
        }
        return cart;
    }
    
    public static void main(String[] args) {
        Cart item = new Cart();
        ArrayList<String> string = new ArrayList<>();
        string.add("12-2");
        string.add("13-2");
        item.decryptionCookiesText(string);
        System.out.println(item.getCart().get(0).getProduct());
    }
    
    
}
