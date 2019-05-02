package gl51.project.store


class MemoryProductStorage implements  ProductStorage {
	private Map<String, Product> productmap = [:]
	private int id_total = 1
    @Override
    void save(Product p) {
    	p.id = id_total
		productmap[p.name] = p
		id_total += 1
    }
	
    @Override
    void update(String id, Product p) {
    	Product product = getByID(id)
    	if(product != null){
    		productmap[product.name] = p
    	}
    }

    @Override
    Product getByID(String id) {
    	for (e in productmap){
    		if (e.value.id == id){
    			Product product = e.value
    			return product 
    		}
    	}
    	throw new NotExistingProductException
    }

    @Override
    void delete(String id) {
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
    
}
