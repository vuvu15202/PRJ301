ALTER TABLE Customers
ADD CreateDate datetime;

select * from Customers

insert into Customers(CustomerID, CompanyName , ContactName,ContactTitle,Address,CreateDate) values('abcd','vu','vuvu','vuvu','vuvu',GETDATE())

select * from Orders o,[Order Details] od where o.OrderID=od.OrderID AND  Year(OrderDate)=YEAR(GETDATE()) AND MONTH(OrderDate)=11

select * from Orders o,[Order Details] od,Customers c where o.OrderID=od.OrderID AND c.CustomerID=o.CustomerID AND  Year(OrderDate)=YEAR(GETDATE()) AND MONTH(CreateDate)=11 AND MONTH(CreateDate)=11

select * from Orders o,[Order Details] od,Customers c where o.OrderID=od.OrderID AND c.CustomerID=o.CustomerID AND  Year(OrderDate)=YEAR(GETDATE()) 
AND MONTH(o.OrderDate)=? AND c.CustomerID NOT IN (select cus.CustomerID from Customers cus, Accounts acc where cus.CustomerID=acc.CustomerID)

select c.CustomerID from Customers c, Accounts a where c.CustomerID=a.CustomerID