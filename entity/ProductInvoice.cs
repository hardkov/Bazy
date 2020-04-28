using System;
using System.Collections.Generic;
using System.Text;
using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace IHardekProductEF
{
    class ProductInvoice
    {

        public int ProductID { get; set; }
    
        public int InvoiceID { get; set; }

        public Product Product { get; set; }
        public Invoice Invoice { get; set; }
    }
}
