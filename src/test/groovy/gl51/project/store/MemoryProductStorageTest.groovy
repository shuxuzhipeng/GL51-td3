package gl51.project.store

import spock.lang.Specification

class MemoryProductStorageTest extends Specification {

    ProductStorage store = new MemoryProductStorage()

    def "empty storage returns empty list"() {
        expect:
        store.all() ==  []
    }

    def "adding a product returns the product in the list"() {
        setup:
        store.save(new Product(name: "myproduct"))

        when:
        def all = store.all()

        then:
        all.size() == 1
        all.first().name == 'myproduct'
    }
    def "adding a product will generate a new id"() {
        setup:
        Product myProduct = new Product(name: "myProduct")
        store.save(myProduct)
    }

    def "deleting a product will remove it from the list"() {
        setup:
        Product myProduct = new Product(name: "myProduct")
        store.save(myProduct)

        when:
        store.delete(myProduct.getID())
		
		then：
		!store.all().contains(myProduct)
    }

    def "modifying a product will change it in the list"() {
        setup:
        Product myProduct = new Product(name: "myProduct")
        store.save(myProduct)

        when:
        Product myUpdatedProduct = new Product(name: "myUpdatedProduct")
        store.update(myProduct.getID(), myUpdatedProduct)

        then:
        myProduct != myUpdatedProduct
    }

    def "getting a product by its id will throw a NotExistingProductException if it doesn't exist"() {
        setup:
        int id = -1

        when:
        store.getByID(id)
		
		then:
		throw NotExistingProductException
    }

    def "getting a product by its id will return it if it does exist"() {
        setup:
        Product myProduct = new Product(name: "myProduct")
        store.save(myProduct)

        when:
        Product gettedProduct = store.getByID(myProduct.getID())

        then:
        myProduct == gettedProduct
    }
		
}
