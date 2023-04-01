/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author user
 */
public class OrderDetail {
    private int OrderID,ProductID,Quantity;
    private double UnitPrice,Discount;

    public OrderDetail(int OrderID, int ProductID, int Quantity, double UnitPrice, double Discount) {
        this.OrderID = OrderID;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
        this.UnitPrice = UnitPrice;
        this.Discount = Discount;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "OrderID=" + OrderID + ", ProductID=" + ProductID + ", Quantity=" + Quantity + ", UnitPrice=" + UnitPrice + ", Discount=" + Discount + '}';
    }
    
    
    
}
