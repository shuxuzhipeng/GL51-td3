package gl51.project.store
class MemoryProductStorage implements  ProductStorage {
	private List<Product> productlist = []
	static int id_total = 0
    @Override
    void save(Product p) {	
		id_total += 1
		p.id = id_total
		productlist.add(p)
    }
	
    @Override
    void update(int id, Product p) {
    	Product product = getByID(id)
		
    	if(product != null){
			int productIndex = productlist.indexof(product)
			productlist.remove(product)
			productlist.add(productIndex,p)
    	}
    }

    @Override
    Product getByID(int id) {
		Product product = productList.find { it.id == id }
        if(product == null)
        {
          throw new NotExistingProductException("The wanted product has not been found!")
        }
        return product
    }

    @Override
    void delete(int id) {
		Product product = getByID(id)
		if (product != null){
			productlist.remove(product)
		}
    }

    @Override
    List<Product> all() {
		return productlist
    }
    
	int getlastID(){
		return id_total
	}
}
