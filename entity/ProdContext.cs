using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.EntityFrameworkCore;


namespace IHardekProductEF
{
    class ProdContext : DbContext
    {
        protected override void OnConfiguring(DbContextOptionsBuilder options) => options.UseSqlite("DataSource=Product.db");

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<ProductInvoice>().HasKey(pi => new { pi.ProductID, pi.InvoiceID});
        }

        public DbSet<Company> Companies { get; set; }

        public DbSet<Customer> Customers { get; set; }
        public DbSet<Product> Products { get; set; }

        public DbSet<Supplier> Suppliers { get; set; }

        public DbSet<Invoice> Invoices { get; set; }

        public DbSet<Category> Categories { get; set; }
        public DbSet<ProductInvoice> ProductInvoice { get; set; }
    }
}
