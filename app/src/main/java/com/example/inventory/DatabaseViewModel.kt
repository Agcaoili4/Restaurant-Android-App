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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale.Category

class DatabaseViewModel(private val repository: AppRepository) : ViewModel() {

    // Owner Query--------------------------------------------------
    val owners = repository.getAllOwners()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun getOwnerByEmail(email: String): Flow<Owner?> {
        return repository.getOwnerByEmail(email)
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                null // initial value
            )
    }

    fun insertOwner(owner: Owner) {
        viewModelScope.launch {
            repository.insertOwner(owner)
        }
    }


    // Customer Query-----------------------------------------------
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


    // Menu Query---------------------------------------------------
    fun insertMenu(menu: Menu) {
        viewModelScope.launch {
            repository.insertMenu(menu)
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

    // Menu operations-----------------------------------------------
    fun updateMenu(menu: Menu) {
        viewModelScope.launch {
            repository.updateMenu(menu)
        }
    }

    fun deleteMenu(menu: Menu) {
        viewModelScope.launch {
            repository.deleteMenu(menu)
        }
    }


    // Order Query---------------------------------------------------
    val orders = repository.getAllOrders()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertOrder(order: Order) {
        viewModelScope.launch {
            repository.insertOrder(order)
        }
    }


    // Order details Query-------------------------------------------
    fun orderdetalis(orderId: Int): Flow<List<OrderDetail>> {
        return repository.getAllOrderDetails(orderId)
    }

    fun insertOrderDetail(orderDetail: OrderDetail) {
        viewModelScope.launch {
            repository.insertOrderDetail(orderDetail)
        }
    }




    // Notification Query----------------------------------------------
    val notifications = repository.getAllNotification()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertNotification(notification: Notification) {
        viewModelScope.launch {
            repository.insertNotification(notification)
        }
    }


    // Insert mockup data------------------------------------------
    fun insertStarterData() {
        viewModelScope.launch {

            // insert sample owner
            repository.insertOwner(
                Owner(
                    email = "sushi@food.ca",
                    password = "1234",
                    restaurantName = "sushi go"
                )
            )
            repository.insertOwner(
                Owner(
                    email = "pizza@food.ca",
                    password = "1234",
                    restaurantName = "pizza go"
                )
            )
            repository.insertOwner(
                Owner(
                    email = "chick@food.ca",
                    password = "1234",
                    restaurantName = "chicken go"
                )
            )

            // insert sample menu
            // sushi
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
                    name = "Tempura Udon",
                    category = "MainDish",
                    description = "Thick udon noodles in broth with shrimp tempura",
                    price = 10.75,
                    image = "https://www.chopstickchronicles.com/wp-content/uploads/2020/06/Tempura-Udon-update-18-e1738984922859.jpg"
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

            // pizza
            repository.insertMenu(
                Menu(
                    ownerId = 2,
                    name = "Margherita Pizza",
                    category = "MainDish",
                    description = "Classic pizza with tomato, mozzarella, and basil",
                    price = 8.99,
                    image = ""
                )
            )
            repository.insertMenu(
                Menu(
                    ownerId = 2,
                    name = "Pepperoni Pizza",
                    category = "MainDish",
                    description = "Spicy pepperoni with mozzarella and tomato sauce",
                    price = 9.99,
                    image = ""
                )
            )
            repository.insertMenu(
                Menu(
                    ownerId = 2,
                    name = "Caesar Salad",
                    category = "Appetizer",
                    description = "Crisp romaine lettuce with Caesar dressing, croutons, and parmesan",
                    price = 6.50,
                    image = ""
                )
            )
            repository.insertMenu(
                Menu(
                    ownerId = 2,
                    name = "Beef Burger",
                    category = "MainDish",
                    description = "Juicy grilled beef patty with lettuce, tomato, and special sauce",
                    price = 10.75,
                    image = ""
                )
            )
            repository.insertMenu(
                Menu(
                    ownerId = 2,
                    name = "Spaghetti Carbonara",
                    category = "MainDish",
                    description = "Spaghetti with creamy egg sauce, pancetta, and parmesan",
                    price = 11.00,
                    image = ""
                )
            )
        }
    }


}