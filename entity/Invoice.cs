using System;
using System.Collections.Generic;
using System.Text;

namespace IHardekProductEF
{
    class Invoice
    {
        public int InvoiceID { get; set; }
        public int Quantity { get; set; }

        public Invoice()
        {
            ProductInvoices = new List<ProductInvoice>();
        }

        public ICollection<ProductInvoice> ProductInvoices { get; set; }

    }
}
