using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace SportStore.Models
{
    public class Product
    {
        public int ProductID { get; set; }
        [Required]
        [StringLength(100)]
        public string Name { get; set; }

        public string Descrition {get; set;}
       
        public decimal Price { get; set; }

        public string Category { get; set; }



    }
}
