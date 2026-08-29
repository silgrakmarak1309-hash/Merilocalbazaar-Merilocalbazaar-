package com.example.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MarketplaceConstants
import com.example.ui.theme.BazaarOrange
import com.example.ui.theme.BazaarOrangeDark
import com.example.ui.theme.NavyDark
import com.example.ui.theme.SlateBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditionsModal(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier.testTag("terms_modal")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 32.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(BazaarOrange.copy(alpha = 0.15f), RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Gavel,
                            contentDescription = null,
                            tint = BazaarOrangeDark,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Terms & Conditions",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Text(
                            text = "Effective Date: 2026 • Meri Local Bazaar",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.testTag("close_terms_button")
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LegalSectionBlock(
                title = "1. Acceptance of Terms",
                content = "By accessing, downloading, installing, or using the Meri Local Bazaar application or related digital services, you acknowledge that you have read, understood, and agree to be bound by these Terms and Conditions."
            )

            LegalSectionBlock(
                title = "2. User Accounts",
                content = "To post advertisements, interact with sellers, or utilize certain marketplace features, you may be required to register an account. You agree to provide accurate, current, and complete registration details and maintain the security of your account credentials."
            )

            LegalSectionBlock(
                title = "3. User Responsibilities & Content Legality",
                content = "Users are solely responsible for the accuracy, truthfulness, and legality of any content, advertisements, product descriptions, pricing, contact information, and communications they post on Meri Local Bazaar. You warrant that your listings do not infringe on intellectual property rights or violate local laws."
            )

            LegalSectionBlock(
                title = "4. Posting Advertisements",
                content = "All advertisements posted must be genuine and accurately describe the item or service being offered in your local area. Duplicate listings, misleading descriptions, or spam postings are strictly prohibited."
            )

            LegalSectionBlock(
                title = "5. Prohibited Content & Items",
                content = "You may not post or trade prohibited items, including but not limited to illegal drugs, weapons, counterfeit goods, hazardous chemicals, stolen property, adult services, or any item prohibited under Indian law and local regulations."
            )

            LegalSectionBlock(
                title = "6. Payments and Paid Features",
                content = "Transactions conducted between buyers and sellers are direct peer-to-peer agreements. Meri Local Bazaar does not act as an escrow agent unless an explicit in-app paid feature is formally provided. Any optional listing promotion fees are clearly displayed prior to purchase."
            )

            LegalSectionBlock(
                title = "7. Content Responsibility & Peer Deals",
                content = "Meri Local Bazaar serves as a digital connecting platform. We do not manufacture, inspect, endorse, or guarantee the physical condition, quality, safety, or legality of items advertised by users. Users are advised to inspect items and verify counterparties safely in person."
            )

            LegalSectionBlock(
                title = "8. Account Suspension or Removal",
                content = "We reserve the right to moderate content, suspend accounts, or terminate access for users who violate these terms, engage in deceptive behavior, or receive verified community complaints."
            )

            LegalSectionBlock(
                title = "9. Changes to Services",
                content = "We may update, modify, or enhance features of the application from time to time. Continued use of the service following modifications constitutes acceptance of the updated terms."
            )

            LegalSectionBlock(
                title = "10. Disclaimer of Warranties",
                content = "The service is provided on an 'as is' and 'as available' basis without warranties of any kind. Meri Local Bazaar disclaims any implied warranties of merchantability, fitness for a specific purpose, or non-infringement to the fullest extent permitted by applicable law."
            )

            LegalSectionBlock(
                title = "11. Contact Information",
                content = "For questions or concerns regarding these Terms & Conditions, please contact us at:\nEmail: ${MarketplaceConstants.SUPPORT_EMAIL}\nWhatsApp: ${MarketplaceConstants.SUPPORT_WHATSAPP_DISPLAY}"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = BazaarOrange),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("I Understand & Close")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyModal(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier.testTag("privacy_modal")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 32.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(BazaarOrange.copy(alpha = 0.15f), RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PrivacyTip,
                            contentDescription = null,
                            tint = BazaarOrangeDark,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Privacy Policy",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Text(
                            text = "Your Privacy Rights • Meri Local Bazaar",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.testTag("close_privacy_button")
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LegalSectionBlock(
                title = "1. Information We Collect",
                content = "Meri Local Bazaar collects only the minimum information necessary to provide, maintain, and improve our digital marketplace services. We respect user privacy and do not sell personal data to third-party brokers."
            )

            LegalSectionBlock(
                title = "2. Account Information",
                content = "When you register, we may collect basic identifiers such as your name, mobile number or email address, and general location/city area to show relevant local listings."
            )

            LegalSectionBlock(
                title = "3. User-Submitted Listings",
                content = "Any details you choose to publish in your public listings—such as item photos, title, price, description, and preferred contact method—will be visible to other marketplace users to facilitate local buyer-seller connections."
            )

            LegalSectionBlock(
                title = "4. How Information Is Used",
                content = "Collected information is used solely for: (a) authenticating your account, (b) displaying local listings in your vicinity, (c) preventing fraud and abuse, and (d) providing customer support."
            )

            LegalSectionBlock(
                title = "5. Data Storage",
                content = "User account records and listing data are stored securely on reliable server infrastructure with restricted access controls."
            )

            LegalSectionBlock(
                title = "6. Security",
                content = "We implement industry-standard administrative and technical safeguards to protect your personal information against unauthorized access, alteration, or disclosure."
            )

            LegalSectionBlock(
                title = "7. Third-Party Services",
                content = "We may use trusted service providers for crash analytics and infrastructure hosting. These partners are bound by strict confidentiality and data protection obligations."
            )

            LegalSectionBlock(
                title = "8. User Rights",
                content = "You have the right to review, update, modify, or delete your account and published advertisements at any time by contacting our support team."
            )

            LegalSectionBlock(
                title = "9. Children's Privacy",
                content = "Meri Local Bazaar is intended for general audiences aged 18 and older. We do not knowingly collect personal information from children under 13."
            )

            LegalSectionBlock(
                title = "10. Changes to Privacy Policy",
                content = "We may update this Privacy Policy periodically. Any revisions will be reflected with an updated effective date within the application."
            )

            LegalSectionBlock(
                title = "11. Contact Information",
                content = "If you have any questions or data privacy requests, contact us at:\nEmail: ${MarketplaceConstants.SUPPORT_EMAIL}\nWhatsApp: ${MarketplaceConstants.SUPPORT_WHATSAPP_DISPLAY}"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = BazaarOrange),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("I Understand & Close")
            }
        }
    }
}

@Composable
private fun LegalSectionBlock(
    title: String,
    content: String
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.5.sp,
                lineHeight = 18.sp
            )
        )
    }
}
