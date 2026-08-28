package com.example.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PostAdd
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.CabDriverListing
import com.example.data.MarketCategory
import com.example.data.MarketListing
import com.example.data.MarketplaceData
import com.example.data.UserProfileData
import com.example.ui.theme.BazaarGold
import com.example.ui.theme.BazaarOrange
import com.example.ui.theme.BazaarOrangeContainer
import com.example.ui.theme.BazaarOrangeDark
import com.example.ui.theme.BazaarTeal
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate300
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import com.example.ui.theme.WhatsAppGreen

enum class AppNavTab(
  val title: String,
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector
) {
  MARKETPLACE("Market", Icons.Filled.Storefront, Icons.Outlined.Storefront),
  CABS("Cab & Taxi", Icons.Filled.DirectionsCar, Icons.Outlined.DirectionsCar),
  POST_AD("Sell Free", Icons.Filled.AddCircle, Icons.Outlined.PostAdd),
  PROFILE("Profile", Icons.Filled.Person, Icons.Outlined.Person)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeriLocalBazaarApp() {
  val context = LocalContext.current

  // Global State
  var currentTab by remember { mutableStateOf(AppNavTab.MARKETPLACE) }
  var isShowingWebPortal by remember { mutableStateOf(false) }

  // Feed State
  val allListings = remember { mutableStateListOf<MarketListing>().apply { addAll(MarketplaceData.INITIAL_LISTINGS) } }
  val allDrivers = remember { mutableStateListOf<CabDriverListing>().apply { addAll(MarketplaceData.INITIAL_DRIVERS) } }
  var selectedCategory by remember { mutableStateOf(MarketCategory.ALL) }
  var searchQuery by remember { mutableStateOf("") }
  var selectedLocation by remember { mutableStateOf("All Locations") }

  // User Profile
  var userProfile by remember {
    mutableStateOf(
      UserProfileData(
        name = "Local User",
        phone = "6009092096",
        location = "Tura, Meghalaya",
        isLoggedIn = true
      )
    )
  }

  // Filter user posted ads
  val userListings = remember(allListings) {
    allListings.filter { it.userCreated }
  }

  if (isShowingWebPortal) {
    // Show Website and APK download portal with easy back button
    Scaffold(
      topBar = {
        CenterAlignedTopAppBar(
          title = {
            Text(
              "Official Website & APK Portal",
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold
            )
          },
          navigationIcon = {
            IconButton(
              onClick = { isShowingWebPortal = false },
              modifier = Modifier.testTag("portal_back_btn")
            ) {
              Icon(Icons.Default.ArrowBack, contentDescription = "Back to App")
            }
          },
          colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Slate900,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
          )
        )
      }
    ) { innerPadding ->
      WebsiteScreen(modifier = Modifier.padding(innerPadding))
    }
  } else {
    Scaffold(
      topBar = {
        MainTopAppBar(
          currentTab = currentTab,
          onWhatsAppSupport = {
            openWhatsAppChat(
              context = context,
              phone = MarketplaceData.SUPPORT_PHONE,
              message = "Hello Meri Local Bazaar Support, I need help."
            )
          },
          onOpenWebPortal = { isShowingWebPortal = true }
        )
      },
      bottomBar = {
        MainBottomNavigation(
          selectedTab = currentTab,
          onTabSelected = { currentTab = it }
        )
      }
    ) { innerPadding ->
      Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
        AnimatedContent(
          targetState = currentTab,
          transitionSpec = { fadeIn() togetherWith fadeOut() },
          label = "TabContent"
        ) { targetTab ->
          when (targetTab) {
            AppNavTab.MARKETPLACE -> {
              MarketplaceFeedScreen(
                listings = allListings,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it },
                searchQuery = searchQuery,
                onSearchQueryChanged = { searchQuery = it },
                selectedLocation = selectedLocation,
                onLocationSelected = { selectedLocation = it },
                onPostAdClick = { currentTab = AppNavTab.POST_AD },
                onListingClick = { /* Handled in dialog */ }
              )
            }

            AppNavTab.CABS -> {
              CabServicesScreen(
                drivers = allDrivers,
                onRegisterDriverClick = { newDriver ->
                  allDrivers.add(0, newDriver)
                }
              )
            }

            AppNavTab.POST_AD -> {
              PostAdScreen(
                onAdPublished = { newListing ->
                  allListings.add(0, newListing)
                },
                onNavigateToFeed = {
                  currentTab = AppNavTab.MARKETPLACE
                  selectedCategory = MarketCategory.ALL
                }
              )
            }

            AppNavTab.PROFILE -> {
              ProfileScreen(
                userProfile = userProfile,
                onProfileUpdated = { userProfile = it },
                userListings = userListings,
                onDeleteListing = { adId ->
                  allListings.removeAll { it.id == adId }
                },
                onOpenWebPortal = { isShowingWebPortal = true }
              )
            }
          }
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
  currentTab: AppNavTab,
  onWhatsAppSupport: () -> Unit,
  onOpenWebPortal: () -> Unit
) {
  Surface(
    color = Slate900,
    shadowElevation = 4.dp
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 10.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      // Left: Logo & App Title
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.testTag("app_brand_header")
      ) {
        Surface(
          shape = CircleShape,
          color = BazaarOrange,
          modifier = Modifier.size(34.dp)
        ) {
          Box(contentAlignment = Alignment.Center) {
            Icon(
              imageVector = Icons.Default.Storefront,
              contentDescription = null,
              tint = Color.White,
              modifier = Modifier.size(20.dp)
            )
          }
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = "Meri Local Bazaar",
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
          }
          Text(
            text = "Buy, Sell & Cab Services",
            fontSize = 11.sp,
            color = BazaarGold,
            fontWeight = FontWeight.Medium
          )
        }
      }

      // Right: Actions (WhatsApp Help & Web Portal)
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        // WhatsApp Support Quick Action
        Surface(
          shape = CircleShape,
          color = WhatsAppGreen.copy(alpha = 0.2f),
          modifier = Modifier
            .size(34.dp)
            .clickable { onWhatsAppSupport() }
            .testTag("topbar_whatsapp_help")
        ) {
          Box(contentAlignment = Alignment.Center) {
            Icon(
              imageVector = Icons.Outlined.Chat,
              contentDescription = "WhatsApp Help",
              tint = WhatsAppGreen,
              modifier = Modifier.size(18.dp)
            )
          }
        }

        // Web Portal View Button
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = Slate800,
          modifier = Modifier
            .clickable { onOpenWebPortal() }
            .testTag("topbar_web_portal")
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              imageVector = Icons.Default.Language,
              contentDescription = "Web Portal",
              tint = Slate300,
              modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("Portal", fontSize = 11.sp, color = Slate200, fontWeight = FontWeight.SemiBold)
          }
        }
      }
    }
  }
}

@Composable
fun MainBottomNavigation(
  selectedTab: AppNavTab,
  onTabSelected: (AppNavTab) -> Unit
) {
  NavigationBar(
    containerColor = MaterialTheme.colorScheme.surface,
    tonalElevation = 8.dp,
    modifier = Modifier.testTag("main_bottom_nav")
  ) {
    AppNavTab.values().forEach { tab ->
      val isSelected = tab == selectedTab
      NavigationBarItem(
        selected = isSelected,
        onClick = { onTabSelected(tab) },
        icon = {
          Icon(
            imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
            contentDescription = tab.title,
            modifier = Modifier.size(24.dp)
          )
        },
        label = {
          Text(
            text = tab.title,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
          )
        },
        colors = NavigationBarItemDefaults.colors(
          selectedIconColor = BazaarOrangeDark,
          selectedTextColor = BazaarOrangeDark,
          indicatorColor = BazaarOrangeContainer,
          unselectedIconColor = Slate500,
          unselectedTextColor = Slate600
        )
      )
    }
  }
}
