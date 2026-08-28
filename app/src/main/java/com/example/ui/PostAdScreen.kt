package com.example.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.outlined.CurrencyRupee
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MarketCategory
import com.example.data.MarketListing
import com.example.data.MarketplaceData
import com.example.ui.theme.BazaarGold
import com.example.ui.theme.BazaarOrange
import com.example.ui.theme.BazaarOrangeContainer
import com.example.ui.theme.BazaarOrangeDark
import com.example.ui.theme.BazaarTeal
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate300
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate50
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import com.example.ui.theme.SuccessGreen

@Composable
fun PostAdScreen(
  onAdPublished: (MarketListing) -> Unit,
  onNavigateToFeed: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current

  var title by remember { mutableStateOf("") }
  var price by remember { mutableStateOf("") }
  var category by remember { mutableStateOf(MarketCategory.ELECTRONICS) }
  var location by remember { mutableStateOf("Tura") }
  var description by remember { mutableStateOf("") }
  var sellerName by remember { mutableStateOf("") }
  var sellerPhone by remember { mutableStateOf("6009092096") }
  var condition by remember { mutableStateOf("Good Condition") }

  var categoryDropdownExpanded by remember { mutableStateOf(false) }
  var locationDropdownExpanded by remember { mutableStateOf(false) }
  var isPublishedSuccess by remember { mutableStateOf(false) }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .testTag("post_ad_form"),
      contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
      item {
        Card(
          shape = RoundedCornerShape(18.dp),
          colors = CardDefaults.cardColors(containerColor = Slate900),
          modifier = Modifier.fillMaxWidth().testTag("post_ad_header_card")
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .background(
                Brush.horizontalGradient(listOf(Slate900, Color(0xFF1E293B), BazaarOrangeDark))
              )
              .padding(16.dp)
          ) {
            Column {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Surface(
                  shape = RoundedCornerShape(6.dp),
                  color = SuccessGreen.copy(alpha = 0.2f),
                  border = BorderStroke(1.dp, SuccessGreen.copy(alpha = 0.5f))
                ) {
                  Text(
                    text = "100% FREE LISTING",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = SuccessGreen,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                  )
                }

                Text("0% Commission", fontSize = 12.sp, color = BazaarGold, fontWeight = FontWeight.Bold)
              }

              Spacer(modifier = Modifier.height(10.dp))
              Text(
                text = "Post Your Free Ad in 60 Seconds",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
              Text(
                text = "Buyers will chat with you directly on WhatsApp or Call.",
                fontSize = 12.sp,
                color = Slate300,
                modifier = Modifier.padding(top = 2.dp)
              )
            }
          }
        }
      }

      item { Spacer(modifier = Modifier.height(16.dp)) }

      // Category Selector
      item {
        Text("Select Category *", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Slate800)
        Spacer(modifier = Modifier.height(4.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { categoryDropdownExpanded = true }
              .testTag("category_selector"),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, Slate300)
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                  imageVector = getCategoryIcon(category),
                  contentDescription = null,
                  tint = BazaarOrange,
                  modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = category.displayName,
                  fontSize = 14.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = MaterialTheme.colorScheme.onSurface
                )
              }
              Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Slate600)
            }
          }

          DropdownMenu(
            expanded = categoryDropdownExpanded,
            onDismissRequest = { categoryDropdownExpanded = false }
          ) {
            MarketCategory.values().filter { it != MarketCategory.ALL }.forEach { cat ->
              DropdownMenuItem(
                text = { Text(cat.displayName, fontSize = 14.sp) },
                leadingIcon = {
                  Icon(getCategoryIcon(cat), contentDescription = null, tint = BazaarOrange)
                },
                onClick = {
                  category = cat
                  categoryDropdownExpanded = false
                }
              )
            }
          }
        }
      }

      item { Spacer(modifier = Modifier.height(14.dp)) }

      // Ad Title
      item {
        Text("Ad Title / Product Name *", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Slate800)
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
          value = title,
          onValueChange = { title = it },
          placeholder = { Text("e.g. Hero Splendor 2021 or iPhone 12 128GB", fontSize = 13.sp) },
          leadingIcon = { Icon(Icons.Default.ShoppingBag, contentDescription = null, tint = Slate500) },
          modifier = Modifier.fillMaxWidth().testTag("post_ad_title_input"),
          singleLine = true,
          shape = RoundedCornerShape(12.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BazaarOrange,
            unfocusedBorderColor = Slate300
          )
        )
      }

      item { Spacer(modifier = Modifier.height(14.dp)) }

      // Price and Condition Row
      item {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Text("Price (₹) *", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Slate800)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
              value = price,
              onValueChange = { price = it },
              placeholder = { Text("₹ 15,000", fontSize = 13.sp) },
              leadingIcon = { Icon(Icons.Outlined.CurrencyRupee, contentDescription = null, tint = Slate500) },
              modifier = Modifier.fillMaxWidth().testTag("post_ad_price_input"),
              singleLine = true,
              shape = RoundedCornerShape(12.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BazaarOrange,
                unfocusedBorderColor = Slate300
              )
            )
          }

          Column(modifier = Modifier.weight(1f)) {
            Text("Condition", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Slate800)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
              value = condition,
              onValueChange = { condition = it },
              placeholder = { Text("Brand New / Like New", fontSize = 13.sp) },
              leadingIcon = { Icon(Icons.Default.Tag, contentDescription = null, tint = Slate500) },
              modifier = Modifier.fillMaxWidth(),
              singleLine = true,
              shape = RoundedCornerShape(12.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BazaarOrange,
                unfocusedBorderColor = Slate300
              )
            )
          }
        }
      }

      item { Spacer(modifier = Modifier.height(14.dp)) }

      // Location
      item {
        Text("Your Location / Market Area *", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Slate800)
        Spacer(modifier = Modifier.height(4.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { locationDropdownExpanded = true },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, Slate300)
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = BazaarOrange, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(location, fontSize = 14.sp, fontWeight = FontWeight.Medium)
              }
              Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Slate600)
            }
          }

          DropdownMenu(
            expanded = locationDropdownExpanded,
            onDismissRequest = { locationDropdownExpanded = false }
          ) {
            MarketplaceData.LOCATIONS.filter { it != "All Locations" }.forEach { loc ->
              DropdownMenuItem(
                text = { Text(loc) },
                onClick = {
                  location = loc
                  locationDropdownExpanded = false
                }
              )
            }
          }
        }
      }

      item { Spacer(modifier = Modifier.height(14.dp)) }

      // Description
      item {
        Text("Product Description *", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Slate800)
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
          value = description,
          onValueChange = { description = it },
          placeholder = { Text("Include details like age, condition, reasons for selling, and features...", fontSize = 13.sp) },
          leadingIcon = { Icon(Icons.Default.Description, contentDescription = null, tint = Slate500) },
          modifier = Modifier.fillMaxWidth().height(110.dp).testTag("post_ad_desc_input"),
          maxLines = 4,
          shape = RoundedCornerShape(12.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BazaarOrange,
            unfocusedBorderColor = Slate300
          )
        )
      }

      item { Spacer(modifier = Modifier.height(14.dp)) }

      // Seller Name and Phone
      item {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Text("Seller Name *", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Slate800)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
              value = sellerName,
              onValueChange = { sellerName = it },
              placeholder = { Text("Your Name", fontSize = 13.sp) },
              leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Slate500) },
              modifier = Modifier.fillMaxWidth().testTag("post_ad_seller_input"),
              singleLine = true,
              shape = RoundedCornerShape(12.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BazaarOrange,
                unfocusedBorderColor = Slate300
              )
            )
          }

          Column(modifier = Modifier.weight(1f)) {
            Text("WhatsApp Phone *", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Slate800)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
              value = sellerPhone,
              onValueChange = { sellerPhone = it },
              placeholder = { Text("6009092096", fontSize = 13.sp) },
              leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = Slate500) },
              modifier = Modifier.fillMaxWidth().testTag("post_ad_phone_input"),
              singleLine = true,
              shape = RoundedCornerShape(12.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BazaarOrange,
                unfocusedBorderColor = Slate300
              )
            )
          }
        }
      }

      item { Spacer(modifier = Modifier.height(20.dp)) }

      // Submit Button
      item {
        Button(
          onClick = {
            if (title.isBlank()) {
              Toast.makeText(context, "Please enter an Ad title", Toast.LENGTH_SHORT).show()
              return@Button
            }
            if (price.isBlank()) {
              Toast.makeText(context, "Please enter price", Toast.LENGTH_SHORT).show()
              return@Button
            }
            if (sellerName.isBlank()) {
              Toast.makeText(context, "Please enter your name", Toast.LENGTH_SHORT).show()
              return@Button
            }

            val formattedPrice = if (price.startsWith("₹")) price else "₹$price"
            val newListing = MarketListing(
              id = "user_ad_${System.currentTimeMillis()}",
              title = title.trim(),
              price = formattedPrice,
              category = category,
              location = location,
              description = description.ifBlank { "Contact seller for complete details." },
              sellerName = sellerName.trim(),
              sellerPhone = sellerPhone.trim().ifBlank { "6009092096" },
              isVerified = true,
              isFeatured = true,
              postedTimeAgo = "Just now",
              condition = condition,
              userCreated = true
            )

            onAdPublished(newListing)
            isPublishedSuccess = true
            Toast.makeText(context, "🎉 Your Free Ad is published live on Meri Local Bazaar!", Toast.LENGTH_LONG).show()
            onNavigateToFeed()
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .testTag("publish_ad_button"),
          colors = ButtonDefaults.buttonColors(containerColor = BazaarOrange),
          shape = RoundedCornerShape(14.dp)
        ) {
          Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color.White)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Publish Free Ad Now (Live Instantly)",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        }
      }

      item { Spacer(modifier = Modifier.height(80.dp)) }
    }
  }
}
