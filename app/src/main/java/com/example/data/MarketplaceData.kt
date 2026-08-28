package com.example.data

import com.example.R

enum class MarketCategory(
  val id: String,
  val displayName: String,
  val iconName: String,
  val description: String
) {
  ALL("all", "All Items", "Storefront", "Browse all local listings"),
  ELECTRONICS("electronics", "Mobiles & Electronics", "PhoneAndroid", "Smartphones, TVs, Laptops & Accessories"),
  VEHICLES("vehicles", "Bikes & Vehicles", "TwoWheeler", "Motorcycles, Scooters, Cars & Commercial Vehicles"),
  CAB_TAXI("cab_taxi", "Cab & Taxi Rides", "LocalTaxi", "Local Taxis, Autos, Intercity & Private Cabs"),
  PROPERTY("property", "Property & Rent", "HomeWork", "Rooms, Houses, Shops for Rent & Land"),
  SERVICES("services", "Services & Jobs", "Build", "Electricians, Plumbers, Drivers & Local Jobs"),
  FASHION("fashion", "Daily Bazaar & Goods", "ShoppingBag", "Clothes, Furniture, Groceries & Local Products")
}

data class MarketListing(
  val id: String,
  val title: String,
  val price: String,
  val category: MarketCategory,
  val location: String,
  val description: String,
  val sellerName: String,
  val sellerPhone: String,
  val isVerified: Boolean = true,
  val isFeatured: Boolean = false,
  val postedTimeAgo: String = "Today",
  val condition: String = "Like New",
  val imageRes: Int? = null,
  val userCreated: Boolean = false
)

data class CabDriverListing(
  val id: String,
  val driverName: String,
  val vehicleType: String,
  val vehicleNumber: String,
  val baseLocation: String,
  val routesCovered: String,
  val fareEstimate: String,
  val phone: String,
  val isAvailableNow: Boolean = true,
  val rating: Float = 4.9f,
  val totalTrips: Int = 120
)

data class UserProfileData(
  val name: String = "Guest User",
  val phone: String = "6009092096",
  val email: String = "merilocalbazaar@gmail.com",
  val location: String = "Local Market Area",
  val isLoggedIn: Boolean = true
)

object MarketplaceData {
  const val SUPPORT_PHONE = "6009092096"
  const val SUPPORT_PHONE_DISPLAY = "+91 6009092096"
  const val SUPPORT_EMAIL = "merilocalbazaar@gmail.com"
  const val SUPPORT_WHATSAPP_LINK = "https://wa.me/916009092096?text=Hello%20Meri%20Local%20Bazaar%20Team%2C%20I%20need%20help"

  val LOCATIONS = listOf(
    "All Locations",
    "Tura",
    "Shillong",
    "Guwahati",
    "Williamnagar",
    "Baghmara",
    "Resubelpara",
    "West Garo Hills",
    "East Garo Hills",
    "South Garo Hills",
    "Other Local Area"
  )

  val INITIAL_LISTINGS = listOf(
    MarketListing(
      id = "item_1",
      title = "Samsung Galaxy S22 5G (128GB - Phantom Black)",
      price = "₹24,999",
      category = MarketCategory.ELECTRONICS,
      location = "Tura, West Garo Hills",
      description = "Mint condition, 100% battery health, complete with original fast charger and bill. No scratches or dents. Price slightly negotiable.",
      sellerName = "Sengman Sangma",
      sellerPhone = "6009092096",
      isVerified = true,
      isFeatured = true,
      postedTimeAgo = "2 hours ago",
      condition = "Like New",
      imageRes = R.drawable.merilocal_hero_mockup
    ),
    MarketListing(
      id = "item_2",
      title = "Hero Splendor Plus (BS6 - 2022 Model)",
      price = "₹48,000",
      category = MarketCategory.VEHICLES,
      location = "Williamnagar",
      description = "Single owner, 18,500 KM driven, insurance valid till 2027. Excellent 65+ km/l mileage. All service records available.",
      sellerName = "Tengrang Marak",
      sellerPhone = "6009092096",
      isVerified = true,
      isFeatured = true,
      postedTimeAgo = "4 hours ago",
      condition = "Excellent",
      imageRes = R.drawable.merilocal_categories
    ),
    MarketListing(
      id = "item_3",
      title = "2 BHK Independent House for Rent",
      price = "₹7,500/mo",
      category = MarketCategory.PROPERTY,
      location = "Hawakhana, Tura",
      description = "Spacious 2 bedrooms, modular kitchen, 24/7 water supply, car parking available. Near main bazaar and convent school.",
      sellerName = "Silse Momin",
      sellerPhone = "6009092096",
      isVerified = true,
      isFeatured = false,
      postedTimeAgo = "Yesterday",
      condition = "Ready to Move"
    ),
    MarketListing(
      id = "item_4",
      title = "HP Pavilion Gaming Laptop (Ryzen 5 / GTX 1650 / 16GB RAM)",
      price = "₹34,500",
      category = MarketCategory.ELECTRONICS,
      location = "Shillong, East Khasi Hills",
      description = "Used for light office & editing work. 512GB NVMe SSD + 1TB HDD. Original charger & laptop bag included.",
      sellerName = "Bantei Lyngdoh",
      sellerPhone = "6009092096",
      isVerified = true,
      isFeatured = false,
      postedTimeAgo = "Yesterday",
      condition = "Very Good"
    ),
    MarketListing(
      id = "item_5",
      title = "Experienced Electrician & Home Wiring Services",
      price = "₹299/visit",
      category = MarketCategory.SERVICES,
      location = "Tura & Nearby Towns",
      description = "Expert in house electrical wiring, inverter setup, fan & MCB repair, switchboard repair. 10+ years experience. Instant service.",
      sellerName = "Babul Ch. Das",
      sellerPhone = "6009092096",
      isVerified = true,
      isFeatured = true,
      postedTimeAgo = "Today",
      condition = "Certified Professional"
    ),
    MarketListing(
      id = "item_6",
      title = "Apple iPhone 13 (128GB - Starlight White)",
      price = "₹37,999",
      category = MarketCategory.ELECTRONICS,
      location = "Tura Market",
      description = "Original Indian purchase with box and cable. Battery health 89%. Face ID, TrueTone all working smoothly.",
      sellerName = "Gilsrang Marak",
      sellerPhone = "6009092096",
      isVerified = true,
      isFeatured = false,
      postedTimeAgo = "3 hours ago",
      condition = "Superb"
    ),
    MarketListing(
      id = "item_7",
      title = "Handmade Garo Traditional Dakmanda & Shawls",
      price = "₹1,850",
      category = MarketCategory.FASHION,
      location = "Tura, Meghalaya",
      description = "Authentic handloom pure cotton Dakmanda and traditional festive shawls. Beautiful hand-woven border designs.",
      sellerName = "Chirengma Handloom",
      sellerPhone = "6009092096",
      isVerified = true,
      isFeatured = false,
      postedTimeAgo = "Today",
      condition = "Brand New"
    ),
    MarketListing(
      id = "item_8",
      title = "Commercial Shop Space on Main Road (350 Sq.Ft)",
      price = "₹12,000/mo",
      category = MarketCategory.PROPERTY,
      location = "Rongram, West Garo Hills",
      description = "Prime commercial location facing main highway. Best for pharmacy, grocery store, clothing boutique, or salon.",
      sellerName = "Rikman Sangma",
      sellerPhone = "6009092096",
      isVerified = true,
      isFeatured = false,
      postedTimeAgo = "2 days ago",
      condition = "Commercial"
    )
  )

  val INITIAL_DRIVERS = listOf(
    CabDriverListing(
      id = "cab_1",
      driverName = "John Marak (Swift Dzire AC)",
      vehicleType = "Sedan Cab (AC)",
      vehicleNumber = "ML 08 E 4321",
      baseLocation = "Tura Super Market Stand",
      routesCovered = "Tura ⇄ Guwahati Airport, Shillong, Dalu, Phulbari & Local",
      fareEstimate = "₹16/km (Best Intercity Rates)",
      phone = "6009092096",
      isAvailableNow = true,
      rating = 4.9f,
      totalTrips = 340
    ),
    CabDriverListing(
      id = "cab_2",
      driverName = "Pranab Sangma (Local Auto-Rickshaw)",
      vehicleType = "Local Auto",
      vehicleNumber = "ML 08 D 7812",
      baseLocation = "Hawakhana Stand",
      routesCovered = "Local Tura City, Chandmari, Araimile, Rongkhon & Bazaar",
      fareEstimate = "₹50 - ₹120 (Standard Meter / Fixed)",
      phone = "6009092096",
      isAvailableNow = true,
      rating = 4.8f,
      totalTrips = 520
    ),
    CabDriverListing(
      id = "cab_3",
      driverName = "Debo Hajong (Bolero Pickup / Commercial)",
      vehicleType = "Commercial Pickup",
      vehicleNumber = "ML 08 C 9904",
      baseLocation = "Rongram Market",
      routesCovered = "House Shifting, Goods Transportation across Garo Hills",
      fareEstimate = "₹800/trip or Negotiable",
      phone = "6009092096",
      isAvailableNow = true,
      rating = 4.9f,
      totalTrips = 180
    ),
    CabDriverListing(
      id = "cab_4",
      driverName = "Aron Momin (Fast Bike Taxi)",
      vehicleType = "Bike Taxi",
      vehicleNumber = "ML 08 G 2108",
      baseLocation = "Chandmari, Tura",
      routesCovered = "Quick single passenger drops across Tura Town",
      fareEstimate = "₹30 - ₹70 Flat Local",
      phone = "6009092096",
      isAvailableNow = true,
      rating = 5.0f,
      totalTrips = 410
    )
  )
}
