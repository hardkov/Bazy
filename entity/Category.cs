using System;
using System.Collections.Generic;
using System.Text;

namespace IHardekProductEF
{
    class Category
    {
        public int CategoryID { get; set; }
        public string Name { get; set; }

        public Category()
        {
            Products = new List<Product>();
        }
        public ICollection<Product> Products { get; set; }

    }
}
