using System;
using System.Collections.Generic;
using System.Text;

namespace IHardekProductEF
{
    class Supplier : Company
    {
        public string BankAccountNumber { get; set; }

        public Supplier() { Products = new List<Product>(); }

        public ICollection<Product> Products { get; set; }

        
    }
}
