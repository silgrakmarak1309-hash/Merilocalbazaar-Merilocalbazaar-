package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.MarketplaceConstants
import com.example.ui.theme.BazaarOrange
import com.example.ui.theme.BazaarOrangeDark
import com.example.ui.theme.BazaarOrangeLight
import com.example.ui.theme.NavyDark
import com.example.ui.theme.SaffronAmber
import com.example.ui.theme.SlateBorder

enum class NavSection(val title: String) {
    HOME("Home"),
    OFFER("Special Offer"),
    CATEGORIES("Categories"),
    FEATURES("Features"),
    HOW_IT_WORKS("How It Works"),
    DOWNLOAD("Download App"),
    ABOUT("About"),
    SUPPORT("Support")
}

@Composable
fun HeaderNavigation(
    selectedSection: NavSection,
    onSelectSection: (NavSection) -> Unit,
    onDownloadClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp)
        ) {
            // Top branding row with Logo & prominent "Download App" button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onSelectSection(NavSection.HOME) }
                        .padding(4.dp)
                        .testTag("brand_logo_header")
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_official_logo),
                        contentDescription = "Meri Local Bazaar Logo",
                        modifier = Modifier
                            .size(42.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = MarketplaceConstants.APP_NAME,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 17.sp
                            )
                        )
                        Text(
                            text = MarketplaceConstants.TAGLINE,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = BazaarOrange,
                                fontWeight = FontWeight.Medium,
                                fontSize = 11.sp
                            )
                        )
                    }
                }

                // Prominent Download App button in the header
                Button(
                    onClick = onDownloadClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BazaarOrange,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(24.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
                    modifier = Modifier
                        .testTag("header_download_button")
                        .height(38.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = "Download",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Download App",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Smooth Horizontal Navigation links row (Home, Features, How It Works, Download App, About, Support)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavSection.values().forEach { section ->
                    val isSelected = selectedSection == section
                    val chipBackground = if (isSelected) {
                        BazaarOrange.copy(alpha = 0.12f)
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant
                    }
                    val chipTextColor = if (isSelected) {
                        BazaarOrange
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                    val borderModifier = if (isSelected) {
                        Modifier.clip(RoundedCornerShape(20.dp))
                    } else {
                        Modifier.clip(RoundedCornerShape(20.dp))
                    }

                    Box(
                        modifier = borderModifier
                            .background(chipBackground)
                            .clickable { onSelectSection(section) }
                            .padding(horizontal = 14.dp, vertical = 7.dp)
                            .testTag("nav_chip_${section.name.lowercase()}"),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = section.title,
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = chipTextColor,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 12.5.sp
                            )
                        )
                    }
                }
            }
        }
    }
}
