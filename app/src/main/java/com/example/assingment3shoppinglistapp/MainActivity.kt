package com.example.assingment3shoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assingment3shoppinglistapp.ui.theme.Assingment3ShoppingListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assingment3ShoppingListAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShoppingListApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ShoppingListApp(modifier: Modifier = Modifier) {
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }
    var shoppingList by remember { mutableStateOf(listOf<ShoppingItem>()) }


    //println('test');
    Column(modifier = modifier.padding(16.dp)) {
        TextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Item Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = itemQuantity,
            onValueChange = { itemQuantity = it },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (itemName.isNotBlank() && itemQuantity.isNotBlank()) {
                    shoppingList = shoppingList + ShoppingItem(itemName, itemQuantity, false)
                    itemName = ""
                    itemQuantity = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(shoppingList) { item ->
                ShoppingListItem(item = item)
            }
        }
    }
}

data class ShoppingItem(val name: String, val quantity: String, var isChecked: Boolean)

@Composable
fun ShoppingListItem(item: ShoppingItem) {
    var checkedState by remember { mutableStateOf(item.isChecked) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = { checkedState = it }
        )
        Text("${item.name} - ${item.quantity}", modifier = Modifier.padding(start = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListAppPreview() {
    Assingment3ShoppingListAppTheme {
        ShoppingListApp()
    }
}
