package com.example.data

data class FeatureItem(
  val id: String,
  val title: String,
  val category: String,
  val description: String,
  val iconName: String,
  val badge: String
)

data class InstallStep(
  val stepNumber: Int,
  val title: String,
  val subtitle: String,
  val instruction: String,
  val tip: String
)

data class FaqItem(
  val id: Int,
  val question: String,
  val answer: String,
  val category: String
)

data class ScreenshotItem(
  val id: String,
  val title: String,
  val subtitle: String,
  val drawableRes: Int
)

object WebsiteData {
  const val BRAND_NAME = "Meri Local Bazaar"
  const val MAIN_HEADING = "Buy, Sell & Discover Locally"
  const val HERO_DESCRIPTION =
    "Meri Local Bazaar is a local marketplace where people can easily buy, sell and discover products and services near them."
  
  const val DEFAULT_APK_URL =
    "https://github.com/silgrakmarak1309-hash/Apna-Local-Bazaar-1/releases/download/v1.0.0/meri_local_bazaar.apk"
  const val BACKUP_APK_URL =
    "https://github.com/silgrakmarak1309-hash/Apna-Local-Bazaar-1/releases/download/v1.0.0/meri_local_bazaar.apk"
  const val WEBSITE_PORTAL_URL =
    "https://github.com/silgrakmarak1309-hash/Apna-Local-Bazaar-1/releases/tag/v1.0.0"
  const val GITHUB_RELEASE_VIEW_URL =
    "https://github.com/silgrakmarak1309-hash/Apna-Local-Bazaar-1/releases/tag/v1.0.0"
  const val GITHUB_REPO_URL =
    "https://github.com/silgrakmarak1309-hash/Apna-Local-Bazaar-1"
  
  const val APP_VERSION = "v1.0.0"
  const val APP_BUILD = "100"
  const val APP_SIZE = "6.3 MB"
  const val MIN_ANDROID = "Android 7.0+"
  const val RELEASE_DATE = "Latest Official Release"
  const val PACKAGE_NAME = "com.aistudio.merilocalbazaar"
  const val REPO_NAME = "silgrakmarak1309-hash/Apna-Local-Bazaar-1"
  const val SUPPORT_WHATSAPP_NUMBER = "6009092096"
  const val SUPPORT_WHATSAPP_DISPLAY = "+91 6009092096"
  const val SUPPORT_WHATSAPP_LINK = "https://wa.me/916009092096?text=Hello%20Meri%20Local%20Bazaar%20Support"
  const val SUPPORT_EMAIL = "merilocalbazaar@gmail.com"

  val FEATURES = listOf(
    FeatureItem(
      id = "buy_sell",
      title = "Buy and sell locally",
      category = "Core Marketplace",
      description = "List unused items in under 60 seconds and connect directly with verified buyers in your neighborhood with zero commissions.",
      iconName = "Storefront",
      badge = "Zero Commission"
    ),
    FeatureItem(
      id = "electronics",
      title = "Mobile phones and electronics",
      category = "Gadgets & Tech",
      description = "Browse high quality new & used smartphones, laptops, smart TVs, audio accessories, and computer peripherals.",
      iconName = "PhoneAndroid",
      badge = "Trending Deals"
    ),
    FeatureItem(
      id = "vehicles",
      title = "Bikes and vehicles",
      category = "Motors",
      description = "Find verified two-wheelers, motorbikes, scooters, family cars, commercial vehicles, and bicycles at direct owner prices.",
      iconName = "TwoWheeler",
      badge = "Verified Sellers"
    ),
    FeatureItem(
      id = "cab_taxi",
      title = "Local Cab & Taxi Services",
      category = "Travel & Rides",
      description = "Book or offer local cab, taxi, auto-rickshaw, and inter-city rides directly with trusted local drivers with zero surge pricing.",
      iconName = "LocalTaxi",
      badge = "Instant Rides"
    ),
    FeatureItem(
      id = "property_services",
      title = "Property and services",
      category = "Real Estate & Home",
      description = "Discover houses for rent, commercial shops, plots, plus local skilled electricians, plumbers, painters, and mechanics.",
      iconName = "HomeWork",
      badge = "Local Experts"
    ),
    FeatureItem(
      id = "business_listings",
      title = "Local business listings",
      category = "Community Trade",
      description = "Discover neighborhood shops, grocery outlets, specialty boutiques, bakeries, and home-based service providers.",
      iconName = "LocalOffer",
      badge = "Support Local"
    ),
    FeatureItem(
      id = "easy_search",
      title = "Easy search",
      category = "Smart Discovery",
      description = "Instant search with smart radius filtering, category filters, sorting by price & distance, and fast keyword matching.",
      iconName = "Search",
      badge = "Fast & Precise"
    ),
    FeatureItem(
      id = "direct_contact",
      title = "Direct WhatsApp & phone contact",
      category = "Communication",
      description = "No middlemen or delay. Initiate direct WhatsApp chat or phone calls with buyers & sellers instantly.",
      iconName = "WhatsApp",
      badge = "Instant Chat"
    )
  )

  val INSTALL_STEPS = listOf(
    InstallStep(
      stepNumber = 1,
      title = "Download the APK",
      subtitle = "Tap the 'Download APK' button above",
      instruction = "Your browser will start downloading the latest official MeriLocalBazaar.apk installation package directly to your phone.",
      tip = "Make sure your internet connection is active during the download."
    ),
    InstallStep(
      stepNumber = 2,
      title = "Open Downloaded File",
      subtitle = "Tap the download completed notification",
      instruction = "Pull down your notification bar and tap on the completed MeriLocalBazaar.apk download, or find it in your Files / Downloads app.",
      tip = "The APK file size is approximately 24.8 MB."
    ),
    InstallStep(
      stepNumber = 3,
      title = "Allow Install from Source",
      subtitle = "If prompted by Android security",
      instruction = "If your phone asks for permission to install apps from Chrome or your File Manager, tap 'Settings' and toggle 'Allow from this source' ON.",
      tip = "This is standard for direct APK downloads outside Google Play Store."
    ),
    InstallStep(
      stepNumber = 4,
      title = "Tap Install & Launch",
      subtitle = "You're all set!",
      instruction = "Tap 'Install' on the prompt. Once finished, tap 'Open' to launch Meri Local Bazaar and start discovering great local deals!",
      tip = "You can immediately browse and post ads without any subscription fees."
    )
  )

  val FAQS = listOf(
    FaqItem(
      id = 1,
      question = "Is Meri Local Bazaar free to use?",
      answer = "Yes, 100%! Browsing local listings, posting ads for your items, and contacting buyers or sellers is completely free of charge with zero commissions.",
      category = "Pricing"
    ),
    FaqItem(
      id = 2,
      question = "How do I post an ad to sell my product or service?",
      answer = "Simply open the Meri Local Bazaar app, tap the '+' Post Ad button, take or upload photos of your item, choose the appropriate category, set your price, and submit. Your listing goes live immediately!",
      category = "Selling"
    ),
    FaqItem(
      id = 3,
      question = "How do I communicate with sellers and buyers?",
      answer = "Every listing has direct WhatsApp and Phone Call buttons. When you tap them, it directly opens WhatsApp or your phone dialer so you can talk to the user in real-time.",
      category = "Contact"
    ),
    FaqItem(
      id = 4,
      question = "Is it safe to install this APK on my Android phone?",
      answer = "Yes, absolutely! This is the official APK directly built and provided by the Meri Local Bazaar team. It is digitally signed, virus-free, and contains no harmful code.",
      category = "Safety"
    ),
    FaqItem(
      id = 5,
      question = "What Android version is required?",
      answer = "Meri Local Bazaar runs smoothly on Android 7.0 (Nougat) and all newer versions, including Android 12, 13, 14, 15 and 16.",
      category = "Compatibility"
    ),
    FaqItem(
      id = 6,
      question = "How do I update to newer versions of the app?",
      answer = "You can revisit this official website at any time and download the latest APK file. Installing the updated APK will upgrade your existing app while keeping your settings intact.",
      category = "Updates"
    )
  )
}
