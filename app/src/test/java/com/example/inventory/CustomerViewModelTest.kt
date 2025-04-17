package com.example.inventory.uiScreen.CustomerScreen

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CustomerViewModelTest {
    private lateinit var viewModel: CustomerViewModel

    @Before
    fun setup() {
        viewModel = CustomerViewModel()
    }

    @Test
    fun `setOrderList adds item to currentOrderList`() = runTest {
        viewModel.setCurrentOrderId(42)
        viewModel.setOrderList(menuId = 5, quantity = 2)

        val items = viewModel.uiState.value.currentOrderList
        assertThat(items).hasSize(1)
        val item = items[0]
        assertThat(item.orderId).isEqualTo(42)
        assertThat(item.menuId).isEqualTo(5)
        assertThat(item.quantity).isEqualTo(2)
    }

    @Test
    fun `removeOrderListItem removes specific item`() = runTest {
        viewModel.setCurrentOrderId(7)
        viewModel.setOrderList(menuId = 3, quantity = 1)
        val item = viewModel.uiState.value.currentOrderList.first()

        viewModel.removeOrderListItem(item)
        val itemsAfterRemoval = viewModel.uiState.value.currentOrderList
        assertThat(itemsAfterRemoval).isEmpty()
    }

    @Test
    fun `resetOrderList clears all items`() = runTest {
        viewModel.setCurrentOrderId(1)
        viewModel.setOrderList(menuId = 2, quantity = 1)
        viewModel.resetOrderList()

        assertThat(viewModel.uiState.value.currentOrderList).isEmpty()
    }
}