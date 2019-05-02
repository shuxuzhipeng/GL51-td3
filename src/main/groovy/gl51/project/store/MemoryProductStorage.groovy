package gl51.project.store
class MemoryProductStorage implements  ProductStorage {
	private Map<String, Product> productmap = [:]
	static int id_total = 0
    @Override
    void save(Product p) {	
		MemoryProductStorage.id_total += 1
		p.id = MemoryProductStorage.id_total
		productmap[p.name] = p
    }
	
    @Override
    void update(int id, Product p) {
    	Product product = getByID(id)
    	if(product != null){
    		productmap[product.name] = p
    	}
    }

    @Override
    Product getByID(int id) {
		boolean bool = false
    	for (e in productmap){
    		if (e.value.id == id){
    			Product product = e.value
				bool = true
    			return product 
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
			productmap.remove(product)
		}
    }

    @Override
    List<Product> all() {
		List<Product> list_result = []
		for (e in productmap) {
			list_result.add(e.value)	
		}
		print(productmap.size())
		return list_result
    }
    
	int getlastID(){
		return MemoryProductStorage.id_total
	}
}
