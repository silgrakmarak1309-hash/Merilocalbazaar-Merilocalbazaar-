package com.example.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Speed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.FaqItem
import com.example.data.FeatureItem
import com.example.data.InstallStep
import com.example.data.WebsiteData
import com.example.ui.theme.BazaarGold
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
import kotlinx.coroutines.launch

@Composable
fun WebsiteScreen(
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val listState = rememberLazyListState()
  val scope = rememberCoroutineScope()

  val apkDownloadUrl = WebsiteData.DEFAULT_APK_URL
  var expandedFaqId by remember { mutableIntStateOf(1) }
  var selectedGalleryTab by remember { mutableIntStateOf(0) }

  fun triggerApkDownload(url: String) {
    try {
      val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      }
      context.startActivity(intent)
      Toast.makeText(context, "Starting APK download...", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
      Toast.makeText(context, "Could not open browser. Link copied!", Toast.LENGTH_SHORT).show()
      val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
      clipboard.setPrimaryClip(ClipData.newPlainText("APK Link", url))
    }
  }

  fun copyToClipboard(text: String, label: String = "Link") {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText(label, text))
    Toast.makeText(context, "$label copied to clipboard!", Toast.LENGTH_SHORT).show()
  }

  fun shareWebsite() {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
      type = "text/plain"
      putExtra(
        Intent.EXTRA_SUBJECT,
        "Download ${WebsiteData.BRAND_NAME} - ${WebsiteData.MAIN_HEADING}"
      )
      putExtra(
        Intent.EXTRA_TEXT,
        "Download ${WebsiteData.BRAND_NAME} Official Android App!\nBuy, Sell & Discover Locally with Zero Commission.\nDownload APK here: $apkDownloadUrl"
      )
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share Meri Local Bazaar"))
  }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    LazyColumn(
      state = listState,
      modifier = Modifier
        .fillMaxSize()
        .testTag("website_scroll_view")
    ) {
      // 1. Navigation Header Bar
      item {
        WebsiteHeader(
          onDownloadClick = { triggerApkDownload(apkDownloadUrl) },
          onShareClick = { shareWebsite() }
        )
      }

      // 2. Hero Section
      item {
        HeroSection(
          apkUrl = apkDownloadUrl,
          onDownloadClick = { triggerApkDownload(apkDownloadUrl) },
          onCopyLink = { copyToClipboard(apkDownloadUrl, "Download APK Link") },
          onScrollToInstall = {
            scope.launch {
              listState.animateScrollToItem(5)
            }
          }
        )
      }

      // 3. App Version & Specs Banner
      item {
        AppVersionCard(
          apkUrl = apkDownloadUrl,
          onDownloadClick = { triggerApkDownload(apkDownloadUrl) }
        )
      }

      // 4. Features Section
      item {
        FeaturesSection()
      }

      // 5. App Screenshots & Showcase
      item {
        ScreenshotsGallerySection(
          selectedTab = selectedGalleryTab,
          onTabSelected = { selectedGalleryTab = it },
          onDownloadClick = { triggerApkDownload(apkDownloadUrl) }
        )
      }

      // 6. Android Installation Instructions
      item {
        InstallationGuideSection(
          onDownloadClick = { triggerApkDownload(apkDownloadUrl) }
        )
      }

      // 7. FAQ Section
      item {
        FaqSection(
          expandedId = expandedFaqId,
          onToggleFaq = { id ->
            expandedFaqId = if (expandedFaqId == id) -1 else id
          }
        )
      }

      // 8. Contact & Support Section
      item {
        ContactSupportSection(
          onWhatsAppClick = {
            try {
              val intent = Intent(Intent.ACTION_VIEW, Uri.parse(WebsiteData.SUPPORT_WHATSAPP_LINK))
              context.startActivity(intent)
            } catch (e: Exception) {
              copyToClipboard(WebsiteData.SUPPORT_WHATSAPP_DISPLAY, "WhatsApp Support Number")
            }
          },
          onEmailClick = {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
              data = Uri.parse("mailto:${WebsiteData.SUPPORT_EMAIL}")
              putExtra(Intent.EXTRA_SUBJECT, "Meri Local Bazaar Inquiry")
            }
            try {
              context.startActivity(intent)
            } catch (e: Exception) {
              copyToClipboard(WebsiteData.SUPPORT_EMAIL, "Support Email")
            }
          },
          onPhoneClick = {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:1800123456"))
            try {
              context.startActivity(intent)
            } catch (e: Exception) {
              copyToClipboard("1800-123-456", "Helpline Number")
            }
          }
        )
      }

      // 9. Footer
      item {
        WebsiteFooter(
          apkUrl = apkDownloadUrl,
          onDownloadClick = { triggerApkDownload(apkDownloadUrl) }
        )
      }
    }
  }
}

@Composable
fun WebsiteHeader(
  onDownloadClick: () -> Unit,
  onShareClick: () -> Unit
) {
  Surface(
    color = Slate900,
    shadowElevation = 4.dp,
    modifier = Modifier
      .fillMaxWidth()
      .testTag("website_header")
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Image(
          painter = painterResource(id = R.drawable.merilocal_bazaar_logo),
          contentDescription = "Meri Local Bazaar Logo",
          modifier = Modifier
            .size(36.dp)
            .clip(RoundedCornerShape(8.dp))
        )
        Column {
          Text(
            text = WebsiteData.BRAND_NAME,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
          Text(
            text = "Official Website & APK Portal",
            style = MaterialTheme.typography.labelSmall,
            color = BazaarOrangeLight
          )
        }
      }

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        IconButton(
          onClick = onShareClick,
          modifier = Modifier
            .size(36.dp)
            .testTag("share_header_button")
        ) {
          Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Share Website",
            tint = Slate200,
            modifier = Modifier.size(18.dp)
          )
        }

        Button(
          onClick = onDownloadClick,
          colors = ButtonDefaults.buttonColors(
            containerColor = BazaarOrange,
            contentColor = Color.White
          ),
          shape = RoundedCornerShape(20.dp),
          contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 6.dp),
          modifier = Modifier
            .height(34.dp)
            .testTag("header_download_button")
        ) {
          Icon(
            imageVector = Icons.Outlined.Download,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "APK",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HeroSection(
  apkUrl: String,
  onDownloadClick: () -> Unit,
  onCopyLink: () -> Unit,
  onScrollToInstall: () -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .background(
        Brush.verticalGradient(
          colors = listOf(
            Slate900,
            Color(0xFF1E1528),
            MaterialTheme.colorScheme.background
          )
        )
      )
      .padding(horizontal = 16.dp, vertical = 24.dp)
  ) {
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Official Tag Badge
      Surface(
        color = Color(0xFF2E1C2B),
        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(1.dp, BazaarOrange.copy(alpha = 0.4f)),
        modifier = Modifier.padding(bottom = 16.dp)
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Icon(
            imageVector = Icons.Default.VerifiedUser,
            contentDescription = null,
            tint = BazaarOrangeLight,
            modifier = Modifier.size(14.dp)
          )
          Text(
            text = "OFFICIAL ANDROID APP & DOWNLOAD PORTAL",
            color = BazaarOrangeLight,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.8.sp
          )
        }
      }

      // App Logo Frame
      Box(
        modifier = Modifier
          .size(80.dp)
          .shadow(16.dp, RoundedCornerShape(20.dp))
          .clip(RoundedCornerShape(20.dp))
          .background(Color.White)
          .border(2.dp, BazaarOrangeLight, RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
      ) {
        Image(
          painter = painterResource(id = R.drawable.merilocal_bazaar_logo),
          contentDescription = "Meri Local Bazaar Official Logo",
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop
        )
      }

      Spacer(modifier = Modifier.height(14.dp))

      Text(
        text = WebsiteData.BRAND_NAME,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Black,
        color = Color.White,
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = WebsiteData.MAIN_HEADING,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.ExtraBold,
        color = BazaarOrangeLight,
        textAlign = TextAlign.Center,
        fontSize = 24.sp
      )

      Spacer(modifier = Modifier.height(12.dp))

      Text(
        text = WebsiteData.HERO_DESCRIPTION,
        style = MaterialTheme.typography.bodyMedium,
        color = Slate200,
        textAlign = TextAlign.Center,
        lineHeight = 22.sp,
        modifier = Modifier.padding(horizontal = 8.dp)
      )

      Spacer(modifier = Modifier.height(18.dp))

      // Highlight Pills
      FlowRow(
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        HighlightPill(icon = Icons.Default.CheckCircle, text = "100% Free Ads", color = SuccessGreen)
        HighlightPill(icon = Icons.Outlined.Chat, text = "Direct WhatsApp Chat", color = WhatsAppGreen)
        HighlightPill(icon = Icons.Default.Security, text = "Verified & Virus-Free", color = BazaarTeal)
      }

      Spacer(modifier = Modifier.height(24.dp))

      // Primary Download APK CTA Button
      Button(
        onClick = onDownloadClick,
        colors = ButtonDefaults.buttonColors(
          containerColor = BazaarOrange,
          contentColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(60.dp)
          .testTag("download_apk_primary_button")
      ) {
        Icon(
          imageVector = Icons.Default.ArrowDownward,
          contentDescription = null,
          modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
          horizontalAlignment = Alignment.Start
        ) {
          Text(
            text = "Download APK (v1.0.0)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
          Text(
            text = "Direct Fast Download • ${WebsiteData.APP_SIZE} • Android 7.0+",
            fontSize = 11.sp,
            color = Color.White.copy(alpha = 0.9f)
          )
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Secondary Utility Actions
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        OutlinedButton(
          onClick = onCopyLink,
          modifier = Modifier
            .weight(1f)
            .height(44.dp)
            .testTag("copy_download_link_button"),
          shape = RoundedCornerShape(12.dp),
          border = BorderStroke(1.dp, Slate600),
          colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
        ) {
          Icon(
            imageVector = Icons.Default.ContentCopy,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(text = "Copy Link", fontSize = 12.sp)
        }

        OutlinedButton(
          onClick = onScrollToInstall,
          modifier = Modifier
            .weight(1f)
            .height(44.dp)
            .testTag("how_to_install_button"),
          shape = RoundedCornerShape(12.dp),
          border = BorderStroke(1.dp, Slate600),
          colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
        ) {
          Icon(
            imageVector = Icons.Default.HelpOutline,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(text = "Install Steps", fontSize = 12.sp)
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      // Hero Mockup Asset Display
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Slate800),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("hero_mockup_card")
      ) {
        Column {
          Image(
            painter = painterResource(id = R.drawable.merilocal_hero_mockup),
            contentDescription = "Meri Local Bazaar App Interface Preview",
            modifier = Modifier
              .fillMaxWidth()
              .aspectRatio(16f / 9f),
            contentScale = ContentScale.Crop
          )
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .background(Slate900)
              .padding(horizontal = 14.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(8.dp)
                  .clip(CircleShape)
                  .background(SuccessGreen)
              )
              Text(
                text = "Live Official APK Ready",
                color = Slate200,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
              )
            }
            Surface(
              color = BazaarOrange.copy(alpha = 0.15f),
              shape = RoundedCornerShape(20.dp),
              border = BorderStroke(1.dp, BazaarOrange.copy(alpha = 0.3f))
            ) {
              Text(
                text = "v1.0.0 • 100% Safe",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = BazaarOrangeLight,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              )
            }
          }
        }
      }
    }
  }
}

@Composable
fun HighlightPill(icon: ImageVector, text: String, color: Color) {
  Surface(
    color = Slate800,
    shape = RoundedCornerShape(50.dp),
    border = BorderStroke(1.dp, color.copy(alpha = 0.3f)),
    modifier = Modifier.padding(horizontal = 4.dp)
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = color,
        modifier = Modifier.size(14.dp)
      )
      Text(
        text = text,
        color = Slate100,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
      )
    }
  }
}

@Composable
fun AppVersionCard(
  apkUrl: String,
  onDownloadClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 12.dp)
      .testTag("app_version_card"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
  ) {
    Column(
      modifier = Modifier.padding(16.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Column {
          Text(
            text = "Latest App Release Info",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Digital Signature Verified • Production Build",
            style = MaterialTheme.typography.bodySmall,
            color = Slate500
          )
        }
        Surface(
          color = BazaarTealContainer,
          shape = RoundedCornerShape(8.dp)
        ) {
          Text(
            text = WebsiteData.APP_VERSION,
            color = BazaarTealDark,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))
      HorizontalDivider(color = MaterialTheme.colorScheme.outline)
      Spacer(modifier = Modifier.height(14.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        VersionMetric(label = "File Size", value = WebsiteData.APP_SIZE)
        VersionMetric(label = "Requires", value = WebsiteData.MIN_ANDROID)
        VersionMetric(label = "Build Code", value = WebsiteData.APP_BUILD)
        VersionMetric(label = "Package", value = "Direct APK")
      }
    }
  }
}

@Composable
fun VersionMetric(label: String, value: String) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = label,
      style = MaterialTheme.typography.labelSmall,
      color = Slate500
    )
    Spacer(modifier = Modifier.height(2.dp))
    Text(
      text = value,
      style = MaterialTheme.typography.bodyMedium,
      fontWeight = FontWeight.SemiBold,
      color = MaterialTheme.colorScheme.onSurface
    )
  }
}

@Composable
fun FeaturesSection() {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 16.dp)
      .testTag("features_section")
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Box(
        modifier = Modifier
          .size(4.dp, 20.dp)
          .clip(RoundedCornerShape(2.dp))
          .background(BazaarOrange)
      )
      Text(
        text = "Key Features & Categories",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
      )
    }

    Text(
      text = "Everything you need to buy and sell locally with speed and safety.",
      style = MaterialTheme.typography.bodyMedium,
      color = Slate500,
      modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
    )

    Column(
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      WebsiteData.FEATURES.forEach { feature ->
        FeatureCard(feature = feature)
      }
    }
  }
}

@Composable
fun FeatureCard(feature: FeatureItem) {
  val iconVector = when (feature.iconName) {
    "Storefront" -> Icons.Default.ShoppingBag
    "PhoneAndroid" -> Icons.Default.PhoneAndroid
    "TwoWheeler" -> Icons.Default.DirectionsBike
    "LocalTaxi" -> Icons.Default.DirectionsCar
    "HomeWork" -> Icons.Default.Home
    "LocalOffer" -> Icons.Default.Store
    "Search" -> Icons.Default.Search
    "WhatsApp" -> Icons.Outlined.Chat
    else -> Icons.Default.CheckCircle
  }

  val iconBgColor = when (feature.id) {
    "buy_sell" -> BazaarOrangeContainer
    "electronics" -> Color(0xFFE0F7FA)
    "vehicles" -> Color(0xFFFFF3E0)
    "cab_taxi" -> Color(0xFFFFFDE7)
    "property_services" -> Color(0xFFF3E5F5)
    "business_listings" -> Color(0xFFE8F5E9)
    "easy_search" -> Color(0xFFEDE7F6)
    "direct_contact" -> Color(0xFFDCF8C6)
    else -> BazaarTealContainer
  }

  val iconTint = when (feature.id) {
    "buy_sell" -> BazaarOrangeDark
    "electronics" -> Color(0xFF00838F)
    "vehicles" -> Color(0xFFE65100)
    "cab_taxi" -> Color(0xFFF57F17)
    "property_services" -> Color(0xFF6A1B9A)
    "business_listings" -> Color(0xFF2E7D32)
    "easy_search" -> Color(0xFF4527A0)
    "direct_contact" -> Color(0xFF075E54)
    else -> BazaarTealDark
  }

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("feature_card_${feature.id}"),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
      verticalAlignment = Alignment.Top,
      horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      Box(
        modifier = Modifier
          .size(44.dp)
          .clip(RoundedCornerShape(12.dp))
          .background(iconBgColor),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = iconVector,
          contentDescription = null,
          tint = iconTint,
          modifier = Modifier.size(24.dp)
        )
      }

      Column(
        modifier = Modifier.weight(1f)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = feature.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
          )
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
          text = feature.description,
          style = MaterialTheme.typography.bodySmall,
          color = Slate600,
          lineHeight = 18.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Surface(
          color = MaterialTheme.colorScheme.surfaceVariant,
          shape = RoundedCornerShape(6.dp)
        ) {
          Text(
            text = feature.badge,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }
      }
    }
  }
}

@Composable
fun ScreenshotsGallerySection(
  selectedTab: Int,
  onTabSelected: (Int) -> Unit,
  onDownloadClick: () -> Unit
) {
  val tabs = listOf("App Overview", "Categories & Listings", "Direct Chat")

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 16.dp)
      .testTag("gallery_section")
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Box(
        modifier = Modifier
          .size(4.dp, 20.dp)
          .clip(RoundedCornerShape(2.dp))
          .background(BazaarTeal)
      )
      Text(
        text = "App Screenshots & Gallery",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
      )
    }

    Text(
      text = "Get a glimpse of the modern user interface inside Meri Local Bazaar.",
      style = MaterialTheme.typography.bodyMedium,
      color = Slate500,
      modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
    )

    TabRow(
      selectedTabIndex = selectedTab,
      containerColor = MaterialTheme.colorScheme.surface,
      contentColor = BazaarOrange,
      indicator = { tabPositions ->
        TabRowDefaults.SecondaryIndicator(
          modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
          color = BazaarOrange
        )
      },
      divider = { HorizontalDivider(color = MaterialTheme.colorScheme.outline) }
    ) {
      tabs.forEachIndexed { index, title ->
        Tab(
          selected = selectedTab == index,
          onClick = { onTabSelected(index) },
          text = {
            Text(
              text = title,
              fontSize = 12.sp,
              fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
              color = if (selectedTab == index) BazaarOrange else Slate600
            )
          }
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("gallery_preview_card"),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = Slate900),
      elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
      Column {
        when (selectedTab) {
          0 -> {
            Image(
              painter = painterResource(id = R.drawable.merilocal_hero_mockup),
              contentDescription = "Meri Local Bazaar App Showcase",
              modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
              contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(14.dp)) {
              Text(
                text = "Clean, Fast & Intuitive Android App",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
              )
              Text(
                text = "Browse high resolution photos, location distance badges, and instant filters.",
                color = Slate400,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
              )
            }
          }
          1 -> {
            Image(
              painter = painterResource(id = R.drawable.merilocal_categories),
              contentDescription = "Marketplace Categories Showcase",
              modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3f),
              contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(14.dp)) {
              Text(
                text = "Organized Into Popular Local Categories",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
              )
              Text(
                text = "From mobile phones and bikes to home rentals and local services.",
                color = Slate400,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
              )
            }
          }
          else -> {
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .background(Slate800)
                .padding(20.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Box(
                modifier = Modifier
                  .size(56.dp)
                  .clip(CircleShape)
                  .background(Color(0xFF25D366).copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Outlined.Chat,
                  contentDescription = null,
                  tint = WhatsAppGreen,
                  modifier = Modifier.size(32.dp)
                )
              }
              Spacer(modifier = Modifier.height(10.dp))
              Text(
                text = "Direct WhatsApp & Phone Integration",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
              )
              Text(
                text = "No waiting for slow email replies. Tap once to initiate a real-time WhatsApp negotiation or phone call with the seller.",
                color = Slate300,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp,
                modifier = Modifier.padding(top = 6.dp, bottom = 12.dp)
              )
              Button(
                onClick = onDownloadClick,
                colors = ButtonDefaults.buttonColors(
                  containerColor = WhatsAppGreen,
                  contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
              ) {
                Text("Experience It In The App (Download APK)", fontSize = 12.sp)
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun InstallationGuideSection(
  onDownloadClick: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 16.dp)
      .testTag("install_guide_section")
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Box(
        modifier = Modifier
          .size(4.dp, 20.dp)
          .clip(RoundedCornerShape(2.dp))
          .background(BazaarGold)
      )
      Text(
        text = "Android Installation Instructions",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
      )
    }

    Text(
      text = "Follow these 4 simple steps to install Meri Local Bazaar on your Android smartphone.",
      style = MaterialTheme.typography.bodyMedium,
      color = Slate500,
      modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
    )

    Column(
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      WebsiteData.INSTALL_STEPS.forEach { step ->
        InstallStepCard(step = step)
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Download button reminder inside installation guide
    Button(
      onClick = onDownloadClick,
      colors = ButtonDefaults.buttonColors(
        containerColor = BazaarOrange,
        contentColor = Color.White
      ),
      shape = RoundedCornerShape(12.dp),
      modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .testTag("install_guide_download_button")
    ) {
      Icon(
        imageVector = Icons.Default.ArrowDownward,
        contentDescription = null,
        modifier = Modifier.size(20.dp)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = "Download MeriLocalBazaar.apk Now",
        fontWeight = FontWeight.Bold
      )
    }
  }
}

@Composable
fun InstallStepCard(step: InstallStep) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("install_step_${step.stepNumber}"),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
      verticalAlignment = Alignment.Top,
      horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      // Step Number Badge
      Box(
        modifier = Modifier
          .size(36.dp)
          .clip(CircleShape)
          .background(BazaarOrange),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = "${step.stepNumber}",
          color = Color.White,
          fontWeight = FontWeight.Bold,
          fontSize = 16.sp
        )
      }

      Column(
        modifier = Modifier.weight(1f)
      ) {
        Text(
          text = step.title,
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = step.subtitle,
          style = MaterialTheme.typography.labelSmall,
          color = BazaarOrangeLight,
          fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
          text = step.instruction,
          style = MaterialTheme.typography.bodySmall,
          color = Slate600,
          lineHeight = 18.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = Slate400,
            modifier = Modifier.size(12.dp)
          )
          Text(
            text = "Tip: ${step.tip}",
            fontSize = 11.sp,
            color = Slate500,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
          )
        }
      }
    }
  }
}

@Composable
fun FaqSection(
  expandedId: Int,
  onToggleFaq: (Int) -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 16.dp)
      .testTag("faq_section")
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Box(
        modifier = Modifier
          .size(4.dp, 20.dp)
          .clip(RoundedCornerShape(2.dp))
          .background(BazaarOrange)
      )
      Text(
        text = "Frequently Asked Questions",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
      )
    }

    Text(
      text = "Answers to common questions about Meri Local Bazaar and APK installation.",
      style = MaterialTheme.typography.bodyMedium,
      color = Slate500,
      modifier = Modifier.padding(top = 4.dp, bottom = 14.dp)
    )

    Column(
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      WebsiteData.FAQS.forEach { faq ->
        FaqAccordionCard(
          faq = faq,
          isExpanded = expandedId == faq.id,
          onToggle = { onToggleFaq(faq.id) }
        )
      }
    }
  }
}

@Composable
fun FaqAccordionCard(
  faq: FaqItem,
  isExpanded: Boolean,
  onToggle: () -> Unit
) {
  val rotationAngle by animateFloatAsState(
    targetValue = if (isExpanded) 180f else 0f,
    label = "faq_chevron_anim"
  )

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp))
      .clickable { onToggle() }
      .testTag("faq_card_${faq.id}"),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = if (isExpanded) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
    ),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = faq.question,
          style = MaterialTheme.typography.titleSmall,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface,
          modifier = Modifier.weight(1f)
        )
        IconButton(
          onClick = onToggle,
          modifier = Modifier.size(28.dp)
        ) {
          Icon(
            imageVector = Icons.Default.ExpandMore,
            contentDescription = if (isExpanded) "Collapse" else "Expand",
            tint = if (isExpanded) BazaarOrange else Slate500,
            modifier = Modifier.rotate(rotationAngle)
          )
        }
      }

      AnimatedVisibility(
        visible = isExpanded,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
      ) {
        Column(
          modifier = Modifier.padding(top = 10.dp)
        ) {
          HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = faq.answer,
            style = MaterialTheme.typography.bodySmall,
            color = Slate600,
            lineHeight = 20.sp
          )
        }
      }
    }
  }
}

@Composable
fun ContactSupportSection(
  onWhatsAppClick: () -> Unit,
  onEmailClick: () -> Unit,
  onPhoneClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 16.dp)
      .testTag("contact_section"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = Slate900),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
  ) {
    Column(
      modifier = Modifier.padding(18.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Box(
        modifier = Modifier
          .size(48.dp)
          .clip(CircleShape)
          .background(BazaarOrange.copy(alpha = 0.2f)),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.HelpOutline,
          contentDescription = null,
          tint = BazaarOrangeLight,
          modifier = Modifier.size(24.dp)
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = "Need Help or Have Questions?",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center
      )

      Text(
        text = "Our support team is always ready to assist you with app downloads or listings.",
        style = MaterialTheme.typography.bodySmall,
        color = Slate400,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
      )

      Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Button(
          onClick = onWhatsAppClick,
          modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .testTag("contact_whatsapp_button"),
          colors = ButtonDefaults.buttonColors(
            containerColor = WhatsAppGreen,
            contentColor = Color.White
          ),
          shape = RoundedCornerShape(10.dp)
        ) {
          Icon(
            imageVector = Icons.Outlined.Chat,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(text = "WhatsApp Support", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Button(
            onClick = onEmailClick,
            modifier = Modifier
              .weight(1f)
              .height(44.dp)
              .testTag("contact_email_button"),
            colors = ButtonDefaults.buttonColors(
              containerColor = BazaarTeal,
              contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Email,
              contentDescription = null,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Email Support", fontSize = 12.sp, fontWeight = FontWeight.Bold)
          }

          Button(
            onClick = onPhoneClick,
            modifier = Modifier
              .weight(1f)
              .height(44.dp)
              .testTag("contact_phone_button"),
            colors = ButtonDefaults.buttonColors(
              containerColor = BazaarOrange,
              contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Phone,
              contentDescription = null,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Helpline", fontSize = 12.sp, fontWeight = FontWeight.Bold)
          }
        }
      }
    }
  }
}

@Composable
fun WebsiteFooter(
  apkUrl: String,
  onDownloadClick: () -> Unit
) {
  Surface(
    color = Color(0xFF070B14),
    modifier = Modifier
      .fillMaxWidth()
      .testTag("website_footer")
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Image(
          painter = painterResource(id = R.drawable.merilocal_bazaar_logo),
          contentDescription = null,
          modifier = Modifier
            .size(24.dp)
            .clip(RoundedCornerShape(6.dp))
        )
        Text(
          text = WebsiteData.BRAND_NAME,
          color = Color.White,
          fontWeight = FontWeight.Bold,
          fontSize = 14.sp
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = "Official Website & APK Download Portal",
        color = Slate400,
        fontSize = 11.sp
      )

      Text(
        text = "https//: Meri Local Bazaar. Website",
        color = BazaarOrangeLight,
        fontSize = 11.sp,
        fontFamily = FontFamily.Monospace,
        modifier = Modifier.padding(top = 2.dp)
      )

      Spacer(modifier = Modifier.height(14.dp))
      HorizontalDivider(color = Slate800)
      Spacer(modifier = Modifier.height(14.dp))

      Text(
        text = "© 2026 Meri Local Bazaar. All Rights Reserved.\nMade for local communities to buy, sell, and discover seamlessly.",
        color = Slate500,
        fontSize = 10.sp,
        textAlign = TextAlign.Center,
        lineHeight = 16.sp
      )

      Spacer(modifier = Modifier.height(10.dp))

      Button(
        onClick = onDownloadClick,
        colors = ButtonDefaults.buttonColors(
          containerColor = BazaarOrange,
          contentColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 8.dp)
      ) {
        Icon(
          imageVector = Icons.Outlined.Download,
          contentDescription = null,
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = "Download Official APK", fontSize = 12.sp, fontWeight = FontWeight.Bold)
      }
    }
  }
}
