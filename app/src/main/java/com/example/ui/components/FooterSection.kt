package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.MarketplaceConstants
import com.example.ui.theme.BazaarOrange
import com.example.ui.theme.BazaarOrangeLight
import com.example.ui.theme.NavyDark
import com.example.ui.theme.SaffronAmber
import com.example.ui.theme.SlateCard
import com.example.ui.theme.WhatsAppGreen

@Composable
fun FooterSection(
    onNavigateHome: () -> Unit,
    onNavigateDownload: () -> Unit,
    onNavigateFeatures: () -> Unit,
    onNavigateAbout: () -> Unit,
    onOpenTerms: () -> Unit,
    onOpenPrivacy: () -> Unit,
    onEmailSupport: () -> Unit,
    onWhatsAppSupport: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = NavyDark,
        contentColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .testTag("footer_section")
        ) {
            // Brand block
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_official_logo),
                    contentDescription = "Meri Local Bazaar Logo",
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(10.dp))
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = MarketplaceConstants.APP_NAME,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 17.sp
                        )
                    )
                    Text(
                        text = MarketplaceConstants.TAGLINE,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = SaffronAmber,
                            fontSize = 11.5.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            HorizontalDivider(
                color = Color.White.copy(alpha = 0.15f),
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Navigation Links Section
            Text(
                text = "Quick Navigation",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 14.sp
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FooterLinkItem(label = "Home", onClick = onNavigateHome)
                FooterLinkItem(label = "Download App (APK)", onClick = onNavigateDownload)
                FooterLinkItem(label = "Features", onClick = onNavigateFeatures)
                FooterLinkItem(label = "About", onClick = onNavigateAbout)
                FooterLinkItem(label = "Terms & Conditions", onClick = onOpenTerms)
                FooterLinkItem(label = "Privacy Policy", onClick = onOpenPrivacy)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Support info in footer
            Text(
                text = "Customer Support",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 14.sp
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Email Support
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onEmailSupport() }
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Support",
                        tint = SaffronAmber,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Email Support: ${MarketplaceConstants.SUPPORT_EMAIL}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 13.sp
                        )
                    )
                }

                // WhatsApp Support
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onWhatsAppSupport() }
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Forum,
                        contentDescription = "WhatsApp Support",
                        tint = WhatsAppGreen,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "WhatsApp Support: ${MarketplaceConstants.SUPPORT_WHATSAPP_DISPLAY}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 13.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            HorizontalDivider(
                color = Color.White.copy(alpha = 0.15f),
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Copyright notice
            Text(
                text = MarketplaceConstants.COPYRIGHT,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun FooterLinkItem(
    label: String,
    onClick: () -> Unit
) {
    Text(
        text = "•  $label",
        style = MaterialTheme.typography.bodyMedium.copy(
            color = Color.White.copy(alpha = 0.85f),
            fontSize = 13.5.sp
        ),
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 2.dp)
    )
}
