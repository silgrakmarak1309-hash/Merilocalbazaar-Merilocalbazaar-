package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocalOffer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BazaarOrange
import com.example.ui.theme.BazaarOrangeDark
import com.example.ui.theme.NavyDark
import com.example.ui.theme.SaffronAmber
import com.example.ui.theme.SlateBorder

data class FeaturedCategoryItem(
    val id: String,
    val emoji: String,
    val title: String,
    val description: String,
    val examples: List<String>,
    val accentColor: Color
)

val FEATURED_CATEGORIES = listOf(
    FeaturedCategoryItem(
        id = "local_services",
        emoji = "🛠️",
        title = "Local Services",
        description = "Find trusted local professionals and services near you.",
        examples = listOf("Electrician", "Plumber", "Carpenter", "Mobile Repairing", "Electronics Repairing", "and more"),
        accentColor = Color(0xFF0284C7) // Sky Blue
    ),
    FeaturedCategoryItem(
        id = "cab_taxi",
        emoji = "🚖",
        title = "Cab & Taxi",
        description = "Find local cab and taxi services for easy travel.",
        examples = listOf("Local Taxi", "Cab Booking", "Airport Drop", "Local Transport"),
        accentColor = Color(0xFFD97706) // Amber
    ),
    FeaturedCategoryItem(
        id = "travelers_transport",
        emoji = "🚐",
        title = "Travelers & Transport",
        description = "Find travelers, passenger transport and travel services near your area.",
        examples = listOf("Travel Vehicles", "Passenger Services", "Local Transport", "Tour Services"),
        accentColor = Color(0xFF059669) // Emerald
    ),
    FeaturedCategoryItem(
        id = "local_sellers",
        emoji = "🏪",
        title = "Local Sellers",
        description = "Discover local shops, sellers and businesses near you.",
        examples = listOf("Mobile Shops", "Electronics", "Fashion", "Furniture", "Local Businesses"),
        accentColor = Color(0xFFE11D48) // Rose
    )
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FeaturedCategoriesSection(
    onCategoryClick: (FeaturedCategoryItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .testTag("featured_categories_section"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Section Header Badge
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = SaffronAmber.copy(alpha = 0.15f),
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "FEATURED CATEGORIES",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = BazaarOrangeDark,
                        letterSpacing = 0.8.sp
                    )
                )
            }
        }

        // Section Title: Explore Local Services & Businesses
        Text(
            text = "Explore Local Services & Businesses",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.testTag("featured_categories_title")
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Discover top-rated services, reliable transport, and neighborhood shops directly in your local area.",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            ),
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Cards List
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            FEATURED_CATEGORIES.forEach { category ->
                FeaturedCategoryCard(
                    category = category,
                    onClick = { onCategoryClick(category) }
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FeaturedCategoryCard(
    category: FeaturedCategoryItem,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("category_card_${category.id}"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Header Row: Emoji icon, Title, Chevron
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Emoji Icon Box
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(category.accentColor.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = category.emoji,
                        fontSize = 24.sp
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = category.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 17.sp
                        )
                    )
                    Text(
                        text = "Verified Local Listings",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = category.accentColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "View category",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Description
            Text(
                text = category.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 20.sp,
                    fontSize = 13.5.sp
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Examples header & pills
            Text(
                text = "Examples:",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                category.examples.forEach { example ->
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                        border = CardDefaults.outlinedCardBorder().copy(
                            brush = Brush.linearGradient(
                                listOf(SlateBorder, SlateBorder.copy(alpha = 0.5f))
                            )
                        )
                    ) {
                        Text(
                            text = example,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Medium,
                                fontSize = 11.5.sp
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
