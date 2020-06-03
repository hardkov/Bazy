using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.EntityFrameworkCore;


namespace IHardekProductEF
{
    class Program
    {
        static void Main(string[] args)
        {
            ProdContext prodContext = new ProdContext();

            Supplier supplier1 = new Supplier
            {
                City = "Katowice",
                CompanyName = "Google",
                Street = "Sienkiewicza",
                Zipcode = "32-611",
                BankAccountNumber = "1231231231232131"
            };

            Supplier supplier2 = new Supplier
            {
                City = "Krakow",
                CompanyName = "Facebook",
                Street = "Bracka",
                Zipcode = "32-600",
                BankAccountNumber = "1238912839123831921231"
            };

            Supplier supplier3 = new Supplier
            {
                City = "Chrzanow",
                CompanyName = "Pepco",
                Street = "Glowna",
                Zipcode = "32-123",
                BankAccountNumber = "123891283912381231239"
            };

            Customer customer1 = new Customer
            {
                City = "Lodz",
                CompanyName = "Super firma",
                Street = "asdasd",
                Zipcode = "23-022",
                Discount = 15
            };

            Customer customer2 = new Customer
            {
                City = "Gdansk",
                CompanyName = "adsasdas",
                Street = "Super ulica",
                Zipcode = "11-111",
                Discount = 1
            };

            prodContext.Companies.Add(supplier1);
            prodContext.Companies.Add(supplier2);
            prodContext.Companies.Add(supplier3);
            prodContext.Companies.Add(customer1);
            prodContext.Companies.Add(customer2);
            prodContext.SaveChanges();

            Console.WriteLine("Suppliers");
            foreach(Supplier c in prodContext.Suppliers)
            {
                Console.WriteLine(c.CompanyName);
            }
            Console.WriteLine("Customers");
            foreach (Customer c in prodContext.Customers)
            {
                Console.WriteLine(c.CompanyName);
            }

        }
    }
}
