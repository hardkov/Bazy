using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text;

namespace IHardekProductEF
{
    class Product
    {
        public int ProductID { get; set; }
        public string Name { get; set; }
        public int UnitsInStock { get; set; }

        public Product()
        {
            ProductInvoices = new List<ProductInvoice>();
        }

        public ICollection<ProductInvoice> ProductInvoices{ get; set; }

        [ForeignKey("SupplierID")]
        public Supplier Supplier { get; set; }

    }
}
