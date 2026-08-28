package com.example.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.MarketCategory
import com.example.data.MarketListing
import com.example.data.MarketplaceData
import com.example.data.WebsiteData
import com.example.ui.theme.BazaarGold
import com.example.ui.theme.BazaarGoldContainer
import com.example.ui.theme.BazaarOrange
import com.example.ui.theme.BazaarOrangeContainer
import com.example.ui.theme.BazaarOrangeDark
import com.example.ui.theme.BazaarOrangeLight
import com.example.ui.theme.BazaarTeal
import com.example.ui.theme.BazaarTealContainer
import com.example.ui.theme.BazaarTealDark
import com.example.ui.theme.Slate100
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
import com.example.ui.theme.WhatsAppGreen

fun openWhatsAppChat(context: Context, phone: String, message: String) {
  try {
    val cleanPhone = phone.replace(Regex("[^0-9]"), "")
    val targetNumber = if (cleanPhone.startsWith("91")) cleanPhone else "91$cleanPhone"
    val encodedMessage = Uri.encode(message)
    val url = "https://wa.me/$targetNumber?text=$encodedMessage"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
      addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(intent)
  } catch (e: Exception) {
    Toast.makeText(context, "Could not open WhatsApp. Phone: +91 $phone", Toast.LENGTH_LONG).show()
  }
}

fun openPhoneDialer(context: Context, phone: String) {
  try {
    val cleanPhone = phone.replace(Regex("[^0-9]"), "")
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$cleanPhone")).apply {
      addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(intent)
  } catch (e: Exception) {
    Toast.makeText(context, "Could not open dialer for $phone", Toast.LENGTH_SHORT).show()
  }
}

fun shareListing(context: Context, listing: MarketListing) {
  try {
    val sendIntent = Intent().apply {
      action = Intent.ACTION_SEND
      putExtra(
        Intent.EXTRA_TEXT,
        "Check out this deal on Meri Local Bazaar:\n\n*${listing.title}*\nPrice: ${listing.price}\nLocation: ${listing.location}\nSeller: ${listing.sellerName} (WhatsApp: ${listing.sellerPhone})\n\nDownload Meri Local Bazaar App: ${WebsiteData.DEFAULT_APK_URL}"
      )
      type = "text/plain"
    }
    context.startActivity(Intent.createChooser(sendIntent, "Share Listing"))
  } catch (e: Exception) {
    Toast.makeText(context, "Sharing unavailable", Toast.LENGTH_SHORT).show()
  }
}

@Composable
fun MarketplaceFeedScreen(
  listings: List<MarketListing>,
  selectedCategory: MarketCategory,
  onCategorySelected: (MarketCategory) -> Unit,
  searchQuery: String,
  onSearchQueryChanged: (String) -> Unit,
  selectedLocation: String,
  onLocationSelected: (String) -> Unit,
  onPostAdClick: () -> Unit,
  onListingClick: (MarketListing) -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var selectedDetailListing by remember { mutableStateOf<MarketListing?>(null) }

  val filteredListings = remember(listings, selectedCategory, searchQuery, selectedLocation) {
    listings.filter { item ->
      val matchesCategory = (selectedCategory == MarketCategory.ALL || item.category == selectedCategory)
      val matchesSearch = searchQuery.isBlank() ||
          item.title.contains(searchQuery, ignoreCase = true) ||
          item.description.contains(searchQuery, ignoreCase = true) ||
          item.location.contains(searchQuery, ignoreCase = true) ||
          item.sellerName.contains(searchQuery, ignoreCase = true)
      val matchesLocation = selectedLocation == "All Locations" ||
          item.location.contains(selectedLocation, ignoreCase = true)
      matchesCategory && matchesSearch && matchesLocation
    }
  }

  Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
    LazyColumn(
      modifier = Modifier.fillMaxSize().testTag("marketplace_feed_list"),
      contentPadding = PaddingValues(bottom = 90.dp)
    ) {
      // 1. Search Bar & Location Row
      item {
        MarketplaceSearchBar(
          searchQuery = searchQuery,
          onQueryChange = onSearchQueryChanged,
          selectedLocation = selectedLocation,
          onLocationClick = {
            // Location selection dialog or cycle
            val currentIndex = MarketplaceData.LOCATIONS.indexOf(selectedLocation)
            val nextIndex = (currentIndex + 1) % MarketplaceData.LOCATIONS.size
            onLocationSelected(MarketplaceData.LOCATIONS[nextIndex])
          }
        )
      }

      // 2. Banner Highlights
      item {
        MarketplaceHeroBanner(onPostAdClick = onPostAdClick)
      }

      // 3. Category Horizontal Pills
      item {
        CategoryHorizontalFilter(
          selectedCategory = selectedCategory,
          onCategorySelected = onCategorySelected
        )
      }

      // 4. Section Title & Results Count
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = if (selectedCategory == MarketCategory.ALL) "Fresh Local Listings" else selectedCategory.displayName,
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              text = "${filteredListings.size} verified items near $selectedLocation",
              fontSize = 12.sp,
              color = Slate500
            )
          }

          if (selectedCategory != MarketCategory.ALL || searchQuery.isNotBlank() || selectedLocation != "All Locations") {
            TextButton(
              onClick = {
                onCategorySelected(MarketCategory.ALL)
                onSearchQueryChanged("")
                onLocationSelected("All Locations")
              },
              modifier = Modifier.testTag("clear_filters_btn")
            ) {
              Text("Reset Filters", fontSize = 13.sp, color = BazaarOrange)
            }
          }
        }
      }

      // 5. Listings Items
      if (filteredListings.isEmpty()) {
        item {
          EmptyListingsState(
            onResetFilters = {
              onCategorySelected(MarketCategory.ALL)
              onSearchQueryChanged("")
              onLocationSelected("All Locations")
            },
            onPostAdClick = onPostAdClick
          )
        }
      } else {
        items(filteredListings, key = { it.id }) { listing ->
          MarketListingCard(
            listing = listing,
            onCardClick = { selectedDetailListing = listing },
            onWhatsAppClick = {
              openWhatsAppChat(
                context = context,
                phone = listing.sellerPhone,
                message = "Hello ${listing.sellerName}, I am interested in your listing '${listing.title}' priced at ${listing.price} on Meri Local Bazaar. Is it still available?"
              )
            },
            onCallClick = {
              openPhoneDialer(context, listing.sellerPhone)
            }
          )
        }
      }
    }

    // Detail Dialog when clicked
    selectedDetailListing?.let { item ->
      ListingDetailDialog(
        listing = item,
        onDismiss = { selectedDetailListing = null },
        onWhatsAppClick = {
          openWhatsAppChat(
            context = context,
            phone = item.sellerPhone,
            message = "Hello ${item.sellerName}, I am interested in your listing '${item.title}' (${item.price}) on Meri Local Bazaar. Please share more details."
          )
        },
        onCallClick = { openPhoneDialer(context, item.sellerPhone) },
        onShareClick = { shareListing(context, item) }
      )
    }
  }
}

@Composable
fun MarketplaceSearchBar(
  searchQuery: String,
  onQueryChange: (String) -> Unit,
  selectedLocation: String,
  onLocationClick: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .background(
        Brush.verticalGradient(
          listOf(BazaarOrange.copy(alpha = 0.15f), Color.Transparent)
        )
      )
      .padding(horizontal = 16.dp, vertical = 12.dp)
  ) {
    // Location chip
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        modifier = Modifier
          .clip(RoundedCornerShape(20.dp))
          .background(BazaarOrangeContainer)
          .clickable { onLocationClick() }
          .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Default.LocationOn,
          contentDescription = null,
          tint = BazaarOrangeDark,
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = selectedLocation,
          fontSize = 13.sp,
          fontWeight = FontWeight.SemiBold,
          color = BazaarOrangeDark
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = "(Tap to change)",
          fontSize = 11.sp,
          color = Slate600
        )
      }

      Surface(
        shape = RoundedCornerShape(12.dp),
        color = SuccessGreen.copy(alpha = 0.15f),
        border = BorderStroke(1.dp, SuccessGreen.copy(alpha = 0.3f))
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = SuccessGreen,
            modifier = Modifier.size(12.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text("100% Free Ads", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SuccessGreen)
        }
      }
    }

    // Search input
    OutlinedTextField(
      value = searchQuery,
      onValueChange = onQueryChange,
      modifier = Modifier
        .fillMaxWidth()
        .testTag("marketplace_search_input"),
      placeholder = {
        Text("Search mobiles, bikes, cabs, rooms, services...", fontSize = 14.sp, color = Slate400)
      },
      leadingIcon = {
        Icon(
          imageVector = Icons.Default.Search,
          contentDescription = "Search",
          tint = BazaarOrange
        )
      },
      trailingIcon = {
        if (searchQuery.isNotEmpty()) {
          IconButton(onClick = { onQueryChange("") }) {
            Icon(Icons.Default.Close, contentDescription = "Clear", tint = Slate500)
          }
        }
      },
      singleLine = true,
      shape = RoundedCornerShape(16.dp),
      colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = BazaarOrange,
        unfocusedBorderColor = Slate300,
        focusedContainerColor = MaterialTheme.colorScheme.surface,
        unfocusedContainerColor = MaterialTheme.colorScheme.surface
      )
    )
  }
}

@Composable
fun MarketplaceHeroBanner(onPostAdClick: () -> Unit) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 6.dp)
      .testTag("marketplace_hero_banner"),
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = Slate900),
    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(
          Brush.horizontalGradient(
            listOf(Slate900, Color(0xFF1E293B), BazaarOrangeDark.copy(alpha = 0.6f))
          )
        )
        .padding(16.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = BazaarGold.copy(alpha = 0.2f),
            border = BorderStroke(1.dp, BazaarGold.copy(alpha = 0.5f))
          ) {
            Text(
              text = "Sell Anything Fast",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = BazaarGold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "Apna Saman Becho & Local Cabs Book Karo",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
          Text(
            text = "Direct WhatsApp chat with buyers & drivers with 0% commission.",
            fontSize = 12.sp,
            color = Slate300,
            modifier = Modifier.padding(top = 2.dp)
          )

          Spacer(modifier = Modifier.height(10.dp))
          Button(
            onClick = onPostAdClick,
            colors = ButtonDefaults.buttonColors(containerColor = BazaarOrange),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
            modifier = Modifier.testTag("banner_post_ad_btn")
          ) {
            Text("+ Post Free Ad Now", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
          }
        }

        Spacer(modifier = Modifier.width(12.dp))
        Surface(
          shape = CircleShape,
          color = BazaarOrange.copy(alpha = 0.2f),
          border = BorderStroke(2.dp, BazaarOrange.copy(alpha = 0.4f)),
          modifier = Modifier.size(54.dp)
        ) {
          Box(contentAlignment = Alignment.Center) {
            Icon(
              imageVector = Icons.Default.Storefront,
              contentDescription = null,
              tint = BazaarOrangeLight,
              modifier = Modifier.size(28.dp)
            )
          }
        }
      }
    }
  }
}

@Composable
fun CategoryHorizontalFilter(
  selectedCategory: MarketCategory,
  onCategorySelected: (MarketCategory) -> Unit
) {
  Column(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
    LazyRow(
      contentPadding = PaddingValues(horizontal = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.testTag("categories_horizontal_row")
    ) {
      items(MarketCategory.values()) { cat ->
        val isSelected = cat == selectedCategory
        FilterChip(
          selected = isSelected,
          onClick = { onCategorySelected(cat) },
          label = {
            Text(
              text = cat.displayName,
              fontSize = 12.sp,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            )
          },
          leadingIcon = {
            Icon(
              imageVector = getCategoryIcon(cat),
              contentDescription = null,
              modifier = Modifier.size(16.dp),
              tint = if (isSelected) BazaarOrangeDark else Slate600
            )
          },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = BazaarOrangeContainer,
            selectedLabelColor = BazaarOrangeDark,
            containerColor = MaterialTheme.colorScheme.surface,
            labelColor = Slate700
          ),
          border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = isSelected,
            selectedBorderColor = BazaarOrange,
            borderColor = Slate300
          ),
          shape = RoundedCornerShape(12.dp)
        )
      }
    }
  }
}

fun getCategoryIcon(cat: MarketCategory): ImageVector {
  return when (cat) {
    MarketCategory.ALL -> Icons.Default.Storefront
    MarketCategory.ELECTRONICS -> Icons.Default.PhoneAndroid
    MarketCategory.VEHICLES -> Icons.Default.DirectionsBike
    MarketCategory.CAB_TAXI -> Icons.Default.DirectionsCar
    MarketCategory.PROPERTY -> Icons.Default.HomeWork
    MarketCategory.SERVICES -> Icons.Default.VerifiedUser
    MarketCategory.FASHION -> Icons.Default.ShoppingBag
  }
}

@Composable
fun MarketListingCard(
  listing: MarketListing,
  onCardClick: () -> Unit,
  onWhatsAppClick: () -> Unit,
  onCallClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 6.dp)
      .clickable { onCardClick() }
      .testTag("listing_card_${listing.id}"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = BorderStroke(1.dp, Slate200),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(modifier = Modifier.fillMaxWidth().padding(14.dp)) {
      // Header row: Category badge & Time
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          shape = RoundedCornerShape(6.dp),
          color = BazaarTealContainer
        ) {
          Text(
            text = listing.category.displayName,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = BazaarTealDark,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
          if (listing.isFeatured) {
            Surface(
              shape = RoundedCornerShape(4.dp),
              color = BazaarGoldContainer,
              border = BorderStroke(1.dp, BazaarGold.copy(alpha = 0.5f)),
              modifier = Modifier.padding(end = 6.dp)
            ) {
              Text(
                "FEATURED",
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = Slate900,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
              )
            }
          }
          Text(
            text = listing.postedTimeAgo,
            fontSize = 11.sp,
            color = Slate400
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Title and Price
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
      ) {
        Text(
          text = listing.title,
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
          text = listing.price,
          fontSize = 18.sp,
          fontWeight = FontWeight.ExtraBold,
          color = BazaarOrangeDark
        )
      }

      Spacer(modifier = Modifier.height(6.dp))

      // Description snippet
      Text(
        text = listing.description,
        fontSize = 13.sp,
        color = Slate600,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Seller & Location
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Default.LocationOn,
          contentDescription = null,
          tint = Slate400,
          modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
          text = listing.location,
          fontSize = 12.sp,
          color = Slate500,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.Verified,
            contentDescription = null,
            tint = BazaarTeal,
            modifier = Modifier.size(13.dp)
          )
          Spacer(modifier = Modifier.width(2.dp))
          Text(
            text = listing.sellerName,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Slate700
          )
        }
      }

      HorizontalDivider(
        modifier = Modifier.padding(vertical = 10.dp),
        color = Slate100
      )

      // Quick Action Buttons (Direct WhatsApp & Call)
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Button(
          onClick = onWhatsAppClick,
          modifier = Modifier
            .weight(1f)
            .height(38.dp)
            .testTag("whatsapp_btn_${listing.id}"),
          colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
          shape = RoundedCornerShape(10.dp),
          contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
          Icon(
            imageVector = Icons.Outlined.Chat,
            contentDescription = "WhatsApp",
            tint = Color.White,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text("WhatsApp Chat", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        OutlinedButton(
          onClick = onCallClick,
          modifier = Modifier
            .height(38.dp)
            .testTag("call_btn_${listing.id}"),
          shape = RoundedCornerShape(10.dp),
          colors = ButtonDefaults.outlinedButtonColors(contentColor = Slate800),
          border = BorderStroke(1.dp, Slate300),
          contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Call,
            contentDescription = "Call",
            tint = Slate800,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text("Call", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        }
      }
    }
  }
}

@Composable
fun ListingDetailDialog(
  listing: MarketListing,
  onDismiss: () -> Unit,
  onWhatsAppClick: () -> Unit,
  onCallClick: () -> Unit,
  onShareClick: () -> Unit
) {
  AlertDialog(
    onDismissRequest = onDismiss,
    confirmButton = {},
    dismissButton = {},
    title = null,
    text = {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("listing_detail_dialog")
      ) {
        // Top row with close
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = BazaarOrangeContainer
          ) {
            Text(
              text = listing.category.displayName,
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = BazaarOrangeDark,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
            )
          }

          IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
            Icon(Icons.Default.Close, contentDescription = "Close", tint = Slate500)
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Price badge
        Text(
          text = listing.price,
          fontSize = 26.sp,
          fontWeight = FontWeight.ExtraBold,
          color = BazaarOrangeDark
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Full Title
        Text(
          text = listing.title,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Location & Condition Row
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = Slate100
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(Icons.Default.LocationOn, contentDescription = null, tint = Slate600, modifier = Modifier.size(13.dp))
              Spacer(modifier = Modifier.width(3.dp))
              Text(listing.location, fontSize = 11.sp, color = Slate700)
            }
          }

          Surface(
            shape = RoundedCornerShape(8.dp),
            color = Slate100
          ) {
            Text(
              "Condition: ${listing.condition}",
              fontSize = 11.sp,
              color = Slate700,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
          text = "Description",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = Slate800
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = listing.description,
          fontSize = 13.sp,
          lineHeight = 18.sp,
          color = Slate600
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Seller Info Box
        Surface(
          shape = RoundedCornerShape(12.dp),
          color = Slate50,
          border = BorderStroke(1.dp, Slate200),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Column {
              Text("Seller Contact", fontSize = 11.sp, color = Slate500)
              Text(listing.sellerName, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Slate900)
              Text("+91 ${listing.sellerPhone}", fontSize = 12.sp, color = BazaarTealDark, fontWeight = FontWeight.Medium)
            }

            Surface(
              shape = CircleShape,
              color = SuccessGreen.copy(alpha = 0.15f)
            ) {
              Icon(
                Icons.Default.Verified,
                contentDescription = null,
                tint = SuccessGreen,
                modifier = Modifier.padding(6.dp).size(20.dp)
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Direct Action buttons
        Button(
          onClick = {
            onWhatsAppClick()
            onDismiss()
          },
          colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.fillMaxWidth().height(44.dp)
        ) {
          Icon(Icons.Outlined.Chat, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
          Spacer(modifier = Modifier.width(8.dp))
          Text("Chat with Seller on WhatsApp", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          OutlinedButton(
            onClick = {
              onCallClick()
              onDismiss()
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.weight(1f).height(40.dp)
          ) {
            Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("Call Seller", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
          }

          OutlinedButton(
            onClick = {
              onShareClick()
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.weight(1f).height(40.dp)
          ) {
            Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("Share Ad", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
          }
        }
      }
    }
  )
}

@Composable
fun EmptyListingsState(
  onResetFilters: () -> Unit,
  onPostAdClick: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(32.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Surface(
      shape = CircleShape,
      color = Slate100,
      modifier = Modifier.size(72.dp)
    ) {
      Box(contentAlignment = Alignment.Center) {
        Icon(
          imageVector = Icons.Outlined.Inventory2,
          contentDescription = null,
          tint = Slate400,
          modifier = Modifier.size(36.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))
    Text(
      text = "No listings found matching your search",
      fontSize = 16.sp,
      fontWeight = FontWeight.Bold,
      color = MaterialTheme.colorScheme.onBackground
    )
    Text(
      text = "Try clearing your filters or be the first person to post an ad in this category!",
      fontSize = 13.sp,
      color = Slate500,
      textAlign = androidx.compose.ui.text.style.TextAlign.Center,
      modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
    )

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      OutlinedButton(onClick = onResetFilters) {
        Text("Reset All Filters")
      }
      Button(
        onClick = onPostAdClick,
        colors = ButtonDefaults.buttonColors(containerColor = BazaarOrange)
      ) {
        Text("+ Post Free Ad")
      }
    }
  }
}
