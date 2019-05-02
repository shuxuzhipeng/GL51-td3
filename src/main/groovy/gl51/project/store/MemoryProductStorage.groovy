package gl51.project.store
class MemoryProductStorage implements  ProductStorage {
	private List<Product> productlist = []
	private int id_total = 1
	@Override
    int save(Product p) {
		p.id = id_total
        productList.add(p)
		id_total += 1
    	return p.id
    }
 
	
    @Override
    void update(int id, Product p) {
    	def product = getByID(id)
		
    	if(product != null){
			int productIndex = productlist.indexof(product)
			productlist.remove(product)
			productlist.add(productIndex,p)
    	}
    }

    @Override
    Product getByID(int id) {
		def product = productList.find { it.id == id }
        if(product == null)
        {
          throw new NotExistingProductException("The wanted product has not been found!")
        }
        return product
    }

    @Override
    void delete(int id) {
		def product = getByID(id)
		if (product != null){
			productlist.remove(product)
		}
    }

    @Override
    List<Product> all() {
		return productlist
    }
    
}
