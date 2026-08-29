package com.example.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MarketplaceConstants
import com.example.ui.components.AboutSection
import com.example.ui.components.DownloadSection
import com.example.ui.components.FeaturedCategoriesSection
import com.example.ui.components.FeaturesSection
import com.example.ui.components.FooterSection
import com.example.ui.components.GrowBusinessCtaSection
import com.example.ui.components.HeaderNavigation
import com.example.ui.components.HeroSection
import com.example.ui.components.HowItWorksSection
import com.example.ui.components.InstallationGuideSection
import com.example.ui.components.NavSection
import com.example.ui.components.PrivacyPolicyModal
import com.example.ui.components.SpecialOfferBanner
import com.example.ui.components.SupportSection
import com.example.ui.components.TermsAndConditionsModal
import com.example.ui.theme.BazaarOrange
import com.example.ui.theme.BazaarOrangeDark
import com.example.ui.theme.NavyDark
import com.example.ui.theme.WhatsAppGreen
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var showTermsModal by remember { mutableStateOf(false) }
    var showPrivacyModal by remember { mutableStateOf(false) }
    var currentNavSection by remember { mutableStateOf(NavSection.HOME) }

    val showBackToTop by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 1
        }
    }

    // Helper functions to open links via Android Intents
    fun openUrl(url: String, fallbackMessage: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, fallbackMessage, Toast.LENGTH_SHORT).show()
        }
    }

    fun openEmail() {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse(MarketplaceConstants.EMAIL_URI)
                putExtra(Intent.EXTRA_SUBJECT, "Inquiry: Meri Local Bazaar App")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // Fallback to web browser or generic action
            openUrl("mailto:${MarketplaceConstants.SUPPORT_EMAIL}", "Opening email client...")
        }
    }

    fun openWhatsApp() {
        openUrl(MarketplaceConstants.WHATSAPP_URI, "Opening WhatsApp Support...")
    }

    fun scrollToSection(section: NavSection) {
        currentNavSection = section
        coroutineScope.launch {
            val targetIndex = when (section) {
                NavSection.HOME -> 0
                NavSection.OFFER -> 1
                NavSection.CATEGORIES -> 2
                NavSection.FEATURES -> 4
                NavSection.HOW_IT_WORKS -> 5
                NavSection.DOWNLOAD -> 6
                NavSection.ABOUT -> 8
                NavSection.SUPPORT -> 9
            }
            listState.animateScrollToItem(targetIndex)
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            HeaderNavigation(
                selectedSection = currentNavSection,
                onSelectSection = { section -> scrollToSection(section) },
                onDownloadClick = {
                    openUrl(
                        MarketplaceConstants.APK_DOWNLOAD_URL,
                        "Starting APK download..."
                    )
                }
            )
        },
        bottomBar = {
            // Sticky Mobile Action Bar
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Download APK button
                    Button(
                        onClick = {
                            openUrl(
                                MarketplaceConstants.APK_DOWNLOAD_URL,
                                "Starting direct APK download..."
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BazaarOrange,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(46.dp)
                            .testTag("sticky_download_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Download APK",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Download APK (6.3 MB)",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.5.sp
                            )
                        )
                    }

                    // WhatsApp Support button
                    Button(
                        onClick = { openWhatsApp() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WhatsAppGreen,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .height(46.dp)
                            .testTag("sticky_whatsapp_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Forum,
                            contentDescription = "WhatsApp",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "WhatsApp",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = showBackToTop,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(0)
                        }
                    },
                    containerColor = NavyDark,
                    contentColor = Color.White,
                    shape = CircleShape,
                    modifier = Modifier
                        .size(44.dp)
                        .testTag("back_to_top_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Back to top",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // 0. Hero Section
            item(key = "hero_section") {
                HeroSection(
                    onDownloadApkClick = {
                        openUrl(
                            MarketplaceConstants.APK_DOWNLOAD_URL,
                            "Downloading Meri Local Bazaar APK..."
                        )
                    },
                    onViewReleaseClick = {
                        openUrl(
                            MarketplaceConstants.GITHUB_RELEASE_URL,
                            "Opening GitHub Releases..."
                        )
                    }
                )
            }

            // 1. Special New User Offer Banner
            item(key = "special_offer_section") {
                SpecialOfferBanner(
                    onGetStartedClick = {
                        openUrl(
                            MarketplaceConstants.APK_DOWNLOAD_URL,
                            "Downloading Meri Local Bazaar APK to claim 1 Month FREE PRO Listing..."
                        )
                    }
                )
            }

            // 2. Featured Categories Section (Explore Local Services & Businesses)
            item(key = "featured_categories_section") {
                FeaturedCategoriesSection(
                    onCategoryClick = { category ->
                        openUrl(
                            MarketplaceConstants.APK_DOWNLOAD_URL,
                            "Downloading APK to explore ${category.title}..."
                        )
                    }
                )
            }

            // 3. Grow Your Local Business CTA Section
            item(key = "grow_business_cta_section") {
                GrowBusinessCtaSection(
                    onPostAdClick = {
                        openUrl(
                            MarketplaceConstants.APK_DOWNLOAD_URL,
                            "Download Meri Local Bazaar app to post your ads and claim 1 Month FREE PRO Listing!"
                        )
                    },
                    onDownloadAppClick = {
                        openUrl(
                            MarketplaceConstants.APK_DOWNLOAD_URL,
                            "Downloading Meri Local Bazaar APK..."
                        )
                    }
                )
            }

            // 4. Features Section
            item(key = "features_section") {
                FeaturesSection()
            }

            // 5. How It Works Section
            item(key = "how_it_works_section") {
                HowItWorksSection()
            }

            // 6. Download Section
            item(key = "download_section") {
                DownloadSection(
                    onDownloadApkClick = {
                        openUrl(
                            MarketplaceConstants.APK_DOWNLOAD_URL,
                            "Downloading Meri Local Bazaar APK..."
                        )
                    },
                    onViewReleaseClick = {
                        openUrl(
                            MarketplaceConstants.GITHUB_RELEASE_URL,
                            "Opening GitHub Releases..."
                        )
                    }
                )
            }

            // 7. Installation Guide Section
            item(key = "installation_guide_section") {
                InstallationGuideSection()
            }

            // 8. About Section
            item(key = "about_section") {
                AboutSection()
            }

            // 9. Support Section
            item(key = "support_section") {
                SupportSection(
                    onEmailSupportClick = { openEmail() },
                    onWhatsAppSupportClick = { openWhatsApp() }
                )
            }

            // 10. Footer Section
            item(key = "footer_section") {
                FooterSection(
                    onNavigateHome = { scrollToSection(NavSection.HOME) },
                    onNavigateDownload = { scrollToSection(NavSection.DOWNLOAD) },
                    onNavigateFeatures = { scrollToSection(NavSection.FEATURES) },
                    onNavigateAbout = { scrollToSection(NavSection.ABOUT) },
                    onOpenTerms = { showTermsModal = true },
                    onOpenPrivacy = { showPrivacyModal = true },
                    onEmailSupport = { openEmail() },
                    onWhatsAppSupport = { openWhatsApp() }
                )
            }
        }
    }

    // Terms & Conditions Modal
    if (showTermsModal) {
        TermsAndConditionsModal(
            onDismiss = { showTermsModal = false }
        )
    }

    // Privacy Policy Modal
    if (showPrivacyModal) {
        PrivacyPolicyModal(
            onDismiss = { showPrivacyModal = false }
        )
    }
}
