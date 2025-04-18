package com.example.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.AppRepository
import com.example.inventory.data.Customer
import com.example.inventory.data.Menu
import com.example.inventory.data.Notification
import com.example.inventory.data.Order
import com.example.inventory.data.OrderDetail
import com.example.inventory.data.Owner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DatabaseViewModel(private val repository: AppRepository) : ViewModel() {

    // Owner Query
    val owners = repository.getAllOwners()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun getOwnerByEmail(email: String): Flow<Owner?> {
        return repository.getOwnerByEmail(email)
            .stateIn(viewModelScope, SharingStarted.Lazily, null)
    }

    fun insertOwner(owner: Owner) {
        viewModelScope.launch {
            repository.insertOwner(owner)
        }
    }

    // Customer Query
    val customer = repository.getAllCustomers()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertCustomer(customer: Customer) {
        viewModelScope.launch {
            repository.insertCustomer(customer)
        }
    }

    suspend fun getCustomerById(customerId: Int): Customer? {
        return repository.getCustomerById(customerId).firstOrNull()
    }

    // Menu Query
    fun insertMenu(menu: Menu) {
        viewModelScope.launch {
            repository.insertMenu(menu)
            println("Inserted new menu: ${menu.name} for ownerId: ${menu.ownerId}")
        }
    }

    fun menus(ownerId: Int): Flow<List<Menu>> {
        return repository.getAllMenus(ownerId)
    }

    suspend fun getMenuById(menuId: Int): Menu? {
        return repository.getMenuById(menuId).firstOrNull()
    }

    fun getMenuByCategory(ownerId: Int, category: String): Flow<List<Menu>> {
        return repository.getMenuByCategory(ownerId, category)
    }

    fun updateMenu(menu: Menu) {
        viewModelScope.launch {
            repository.updateMenu(menu)
            println("Updated menu: ${menu.name}")
        }
    }

    fun deleteMenu(menu: Menu) {
        viewModelScope.launch {
            repository.deleteMenu(menu)
            println("Deleted menu: ${menu.name}")
        }
    }

    // Order Query
    val orders = repository.getAllOrders()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertOrder(order: Order) {
        viewModelScope.launch {
            repository.insertOrder(order)
        }
    }

    // Order Detail Query
    fun orderdetalis(orderId: Int) = repository.getAllOrderDetails(orderId)

    fun insertOrderDetail(orderDetail: OrderDetail) {
        viewModelScope.launch {
            repository.insertOrderDetail(orderDetail)
        }
    }

    // Notification Query
    val notifications = repository.getAllNotification()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertNotification(notification: Notification) {
        viewModelScope.launch {
            repository.insertNotification(notification)
        }
    }

    // Insert Starter Data:
    // This function inserts sample owners and menus into the database.
    fun insertStarterData() {
        viewModelScope.launch {
            // Insert sample owners
            repository.insertOwner(
                Owner(
                    email = "sushi@food.ca",
                    password = "1234",
                    restaurantName = "Sushi Go"
                )
            )
            repository.insertOwner(
                Owner(
                    email = "pizza@food.ca",
                    password = "1234",
                    restaurantName = "Pizza Go"
                )
            )
            repository.insertOwner(
                Owner(
                    email = "chick@food.ca",
                    password = "1234",
                    restaurantName = "Chicken Go"
                )
            )

            // Insert sample menus for ownerId 1 (Sushi Go)
            repository.insertMenu(
                Menu(
                    ownerId = 1,
                    name = "Sushi Platter",
                    category = "MainDish",
                    description = "Assortment of fresh nigiri and rolls",
                    price = 18.50,
                    image = "https://images.contentstack.io/v3/assets/bltcedd8dbd5891265b/blt6542458a3d1e8c6f/664cbc3d213dc5f7fd48a20e/origin-of-sushi-hero.jpeg"
                )
            )
            repository.insertMenu(
                Menu(
                    ownerId = 1,
                    name = "Salmon Sashimi",
                    category = "MainDish",
                    description = "Freshly sliced salmon served with wasabi and soy sauce",
                    price = 12.00,
                    image = "https://lenaskitchenblog.com/wp-content/uploads/2021/08/Salmon-Sushi-8.jpg"
                )
            )
            repository.insertMenu(
                Menu(
                    ownerId = 1,
                    name = "Miso Soup",
                    category = "Appetizer",
                    description = "Traditional miso soup with tofu, seaweed, and green onion",
                    price = 3.50,
                    image = "https://www.gimmesomeoven.com/wp-content/uploads/2016/01/Miso-Soup-Recipe-9.jpg"
                )
            )
            repository.insertMenu(
                Menu(
                    ownerId = 1,
                    name = "Edamame",
                    category = "Appetizer",
                    description = "Lightly salted boiled soybeans in the pod",
                    price = 4.00,
                    image = "https://media.post.rvohealth.io/wp-content/uploads/2024/05/close-up-edamame-1296x728-header.jpg"
                )
            )
            repository.insertMenu(
                Menu(
                    ownerId = 1,
                    name = "Matcha Ice Cream",
                    category = "Dessert",
                    description = "Green tea flavored ice cream with a creamy texture",
                    price = 5.25,
                    image = "https://www.justonecookbook.com/wp-content/uploads/2021/08/Green-Tea-Ice-Cream-0099-I-1.jpg"
                )
            )
            repository.insertMenu(
                Menu(
                    ownerId = 1,
                    name = "Takoyaki",
                    category = "Appetizer",
                    description = "Octopus-filled batter balls topped with sauce, mayo, and bonito flakes",
                    price = 6.75,
                    image = "https://www.finedininglovers.com/sites/default/files/recipe_content_images/takoyaki-recipe%C2%A9iStock.jpg"
                )
            )

            // Insert sample menus for ownerId 2 (Pizza Go)
            repository.insertMenu(
                Menu(
                    ownerId = 2,
                    name = "Margherita Pizza",
                    category = "MainDish",
                    description = "Classic pizza with tomato, mozzarella, and basil",
                    price = 8.99,
                    image = "https://via.placeholder.com/400x300.png?text=Margherita+Pizza"
                )
            )
            repository.insertMenu(
                Menu(
                    ownerId = 2,
                    name = "Pepperoni Pizza",
                    category = "MainDish",
                    description = "Spicy pepperoni with mozzarella and tomato sauce",
                    price = 9.99,
                    image = "https://via.placeholder.com/400x300.png?text=Pepperoni+Pizza"
                )
            )
            repository.insertMenu(
                Menu(
                    ownerId = 2,
                    name = "Caesar Salad",
                    category = "Appetizer",
                    description = "Crisp romaine lettuce with Caesar dressing, croutons, and parmesan",
                    price = 6.50,
                    image = "https://via.placeholder.com/400x300.png?text=Caesar+Salad"
                )
            )
        }
    }
}
