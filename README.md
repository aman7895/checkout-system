## checkout-system

This is a **Checkout System** prototype for an online electronics store owner. 

The current version of this system has a number of features available for the store owner and user (who is adding products to their basket on the store). These features include, and are not limited to, adding/updating/removing/applying discounts and deals on products for the store owner. At the same time, the user can add/update/remove products and their quantity from the basket, while calculating the total price as well.

#### project-specifications:

    - The system was created using Spring Boot, JPA (Spring Data) and a MySQL DB.
    - All the operations are supported through the RESTful web service.
    - The software development was done using a Test-driven approach.
    - To enhance the API usage and understanding, a Swagger UI is available with the application. (`/swagger-ui.html`)

#### usage:

The following table shows the REST API endpoints, explaining the usage of this application along with an explanation. The technical implementation of these endpoints can be seen in the swagger.

Path | Method | Role | Explanation
-----|--------|------|------------
`/createProduct` | `POST` | Store Owner | creates a new product using the product object and saves it to DB
`/product/removeProduct/{id}` | `DELETE` | Store Owner | removes the product from the DB using the product-id
`/products` | `GET` | Store Owner | gets all the products from the DB in the form of the product object
`/updateProduct/{id}` | `PUT` | Store Owner | updates a product using the product-id
`/basket/createBasket` | `POST` | User | creates a new basket for the user and returns the unique basket-id
`/basket/{basketId}` | `GET` | User | this gives the basket of the user with all the products, free items and costs with deals applied
`/basket/addProduct/{basketId}/{productId}/{quantity}` | `PUT` | User | adds a product to users basket based on their basked-id, the product they wish to add and it's quantity
 











mvn clean install - will create a jar with automated tests and install the dependencies