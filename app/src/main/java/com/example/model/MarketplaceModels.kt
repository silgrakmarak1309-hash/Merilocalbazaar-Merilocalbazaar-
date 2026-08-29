package com.example.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector

object MarketplaceConstants {
    const val APP_NAME = "Meri Local Bazaar"
    const val TAGLINE = "Apne Area Ka Digital Marketplace!"
    const val HERO_DESCRIPTION = "Buy, sell and discover products, services and local businesses near you."
    
    const val VERSION = "v1.0.0"
    const val PLATFORM = "Android"
    const val APP_SIZE = "6.3 MB"
    const val FILENAME = "meri_local_bazaar.apk"
    
    const val APK_DOWNLOAD_URL = "https://github.com/silgrakmarak1309-hash/Apna-Local-Bazaar-1/releases/download/v1.0.0/meri_local_bazaar.apk"
    const val GITHUB_RELEASE_URL = "https://github.com/silgrakmarak1309-hash/Apna-Local-Bazaar-1/releases/tag/v1.0.0"
    
    const val SUPPORT_EMAIL = "merilocalbazaar@gmail.com"
    const val EMAIL_URI = "mailto:merilocalbazaar@gmail.com"
    
    const val SUPPORT_WHATSAPP_DISPLAY = "+91 6009092096"
    const val WHATSAPP_URI = "https://wa.me/916009092096"
    
    const val COPYRIGHT = "© 2026 Meri Local Bazaar. All rights reserved."
}

data class FeatureItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val category: String,
    val highlightBadge: String? = null
)

val MARKETPLACE_FEATURES = listOf(
    FeatureItem(
        title = "Buy and Sell Locally",
        description = "Discover great deals on second-hand or brand-new goods directly within your neighborhood.",
        icon = Icons.Default.ShoppingBag,
        category = "Marketplace",
        highlightBadge = "Popular"
    ),
    FeatureItem(
        title = "Post Ads and Listings",
        description = "Create and publish classified ads in seconds with clear photos, price tags, and local pickup area.",
        icon = Icons.Default.PostAdd,
        category = "Marketplace",
        highlightBadge = "Free"
    ),
    FeatureItem(
        title = "Mobile Phones",
        description = "Buy and sell smartphones, feature phones, tablets, smartwatches, and original accessories.",
        icon = Icons.Default.PhoneAndroid,
        category = "Category"
    ),
    FeatureItem(
        title = "Electronics",
        description = "Find verified listings for laptops, TVs, home theater, gaming gear, refrigerators, and appliances.",
        icon = Icons.Default.Devices,
        category = "Category"
    ),
    FeatureItem(
        title = "Vehicles",
        description = "Explore second-hand motorcycles, scooters, family cars, commercial vehicles, and auto parts.",
        icon = Icons.Default.DirectionsCar,
        category = "Category"
    ),
    FeatureItem(
        title = "Property and Rentals",
        description = "Search nearby flats, houses, commercial shops, PG rooms, and plots available for rent or purchase.",
        icon = Icons.Default.HomeWork,
        category = "Category"
    ),
    FeatureItem(
        title = "Jobs",
        description = "Find local job vacancies including office staff, sales, drivers, delivery, tuition, and skilled labor.",
        icon = Icons.Default.Work,
        category = "Category"
    ),
    FeatureItem(
        title = "Fashion",
        description = "Shop ethnic wear, trendy clothing, footwear, traditional jewelry, and seasonal collections.",
        icon = Icons.Default.Storefront,
        category = "Category"
    ),
    FeatureItem(
        title = "Local Services",
        description = "Hire trusted nearby electricians, plumbers, painters, appliance repair technicians, and carpenters.",
        icon = Icons.Default.Business,
        category = "Services"
    ),
    FeatureItem(
        title = "Local Shops and Businesses",
        description = "Connect with neighborhood retail shops, bakeries, grocery stores, and local artisans.",
        icon = Icons.Default.Storefront,
        category = "Business"
    ),
    FeatureItem(
        title = "Easy Search",
        description = "Powerful search with instant category filters, distance proximity, and price range sorting.",
        icon = Icons.Default.Search,
        category = "Feature"
    ),
    FeatureItem(
        title = "Contact Sellers Easily",
        description = "Chat directly and securely with sellers, discuss prices, and finalize deals with zero middleman fees.",
        icon = Icons.Default.Chat,
        category = "Feature"
    )
)

data class StepItem(
    val stepNumber: Int,
    val title: String,
    val description: String
)

val HOW_IT_WORKS_STEPS = listOf(
    StepItem(
        stepNumber = 1,
        title = "Download Meri Local Bazaar",
        description = "Get the official lightweight APK file (v1.0.0, ~6.3 MB) safely from our direct download portal."
    ),
    StepItem(
        stepNumber = 2,
        title = "Install the App",
        description = "Open the downloaded APK and follow the quick Android installation prompt on your phone."
    ),
    StepItem(
        stepNumber = 3,
        title = "Create an Account",
        description = "Set up your profile with your name and local area location in less than a minute."
    ),
    StepItem(
        stepNumber = 4,
        title = "Browse Local Products and Services",
        description = "Explore genuine categorized listings posted by verified sellers and businesses near you."
    ),
    StepItem(
        stepNumber = 5,
        title = "Post Your Ads and Listings",
        description = "Snap pictures of what you want to sell or offer, enter your price, and publish for free."
    ),
    StepItem(
        stepNumber = 6,
        title = "Connect with Buyers and Sellers Easily",
        description = "Reach out through built-in chat or direct communication to finalize deals and arrange local pickup."
    )
)

val INSTALLATION_STEPS = listOf(
    "Click the Download APK button on this page.",
    "Wait for the APK download to finish on your device.",
    "Open the downloaded APK file from your notification bar or Downloads folder.",
    "If Android displays an installation warning, allow installation from the browser or file manager only if you trust the source.",
    "Install Meri Local Bazaar by tapping 'Install'.",
    "Open the app and start exploring your local marketplace!"
)
