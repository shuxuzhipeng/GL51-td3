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
		boolean bool = false
    	for (e in productlist){
    		if (e.id == id){
				bool = true
    			return e
    		}
    	}
		if(bool == false){
			throw new NotExistingProductException()
		}
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
