package com.example.ui

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MarketListing
import com.example.data.MarketplaceData
import com.example.data.UserProfileData
import com.example.ui.theme.BazaarGold
import com.example.ui.theme.BazaarOrange
import com.example.ui.theme.BazaarOrangeContainer
import com.example.ui.theme.BazaarOrangeDark
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

@Composable
fun ProfileScreen(
  userProfile: UserProfileData,
  onProfileUpdated: (UserProfileData) -> Unit,
  userListings: List<MarketListing>,
  onDeleteListing: (String) -> Unit,
  onOpenWebPortal: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var showEditProfileDialog by remember { mutableStateOf(false) }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    LazyColumn(
      modifier = Modifier.fillMaxSize().testTag("profile_screen_list"),
      contentPadding = PaddingValues(bottom = 90.dp)
    ) {
      // 1. User Header Banner
      item {
        ProfileHeaderCard(
          userProfile = userProfile,
          onEditProfile = { showEditProfileDialog = true }
        )
      }

      // 2. My Posted Ads Section
      item {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "My Posted Ads (${userListings.size})",
              fontSize = 17.sp,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onBackground
            )
            Surface(
              shape = RoundedCornerShape(6.dp),
              color = BazaarTealContainer
            ) {
              Text(
                "Active",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = BazaarTealDark,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
          }

          if (userListings.isEmpty()) {
            Card(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
              shape = RoundedCornerShape(12.dp),
              colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
              border = BorderStroke(1.dp, Slate200)
            ) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                Text("You haven't posted any ads yet.", fontSize = 13.sp, color = Slate600)
                Text(
                  "Post your first ad to buy, sell, or offer cabs in your local area!",
                  fontSize = 12.sp,
                  color = Slate400,
                  modifier = Modifier.padding(top = 2.dp)
                )
              }
            }
          }
        }
      }

      // User Ads Items
      items(userListings, key = { it.id }) { listing ->
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          border = BorderStroke(1.dp, Slate200)
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(listing.title, fontSize = 14.sp, fontWeight = FontWeight.Bold, maxLines = 1)
              Text(
                "${listing.price} • ${listing.category.displayName}",
                fontSize = 12.sp,
                color = BazaarOrangeDark,
                fontWeight = FontWeight.SemiBold
              )
              Text(listing.location, fontSize = 11.sp, color = Slate500)
            }

            IconButton(
              onClick = {
                onDeleteListing(listing.id)
                Toast.makeText(context, "Listing removed", Toast.LENGTH_SHORT).show()
              }
            ) {
              Icon(Icons.Default.Delete, contentDescription = "Delete Ad", tint = Color(0xFFE53935))
            }
          }
        }
      }

      // 3. Quick Options & Support
      item {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
          Text(
            text = "Helpline & Official Support",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 8.dp)
          )

          Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, Slate200),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.fillMaxWidth()) {
              // WhatsApp Support Row
              ProfileOptionRow(
                icon = Icons.Outlined.Chat,
                iconTint = WhatsAppGreen,
                title = "24/7 WhatsApp Helpline",
                subtitle = "+91 6009092096 (Direct Chat Support)",
                onClick = {
                  openWhatsAppChat(
                    context = context,
                    phone = MarketplaceData.SUPPORT_PHONE,
                    message = "Hello Meri Local Bazaar Team, I need help with my account/listing."
                  )
                }
              )

              HorizontalDivider(color = Slate100, modifier = Modifier.padding(horizontal = 16.dp))

              // Phone Support Row
              ProfileOptionRow(
                icon = Icons.Default.Call,
                iconTint = BazaarTeal,
                title = "Call Support",
                subtitle = MarketplaceData.SUPPORT_PHONE_DISPLAY,
                onClick = {
                  openPhoneDialer(context, MarketplaceData.SUPPORT_PHONE)
                }
              )

              HorizontalDivider(color = Slate100, modifier = Modifier.padding(horizontal = 16.dp))

              // Email Support Row
              ProfileOptionRow(
                icon = Icons.Default.Email,
                iconTint = BazaarOrange,
                title = "Official Email",
                subtitle = MarketplaceData.SUPPORT_EMAIL,
                onClick = {
                  try {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                      data = android.net.Uri.parse("mailto:${MarketplaceData.SUPPORT_EMAIL}")
                      putExtra(Intent.EXTRA_SUBJECT, "Support Query - Meri Local Bazaar")
                    }
                    context.startActivity(intent)
                  } catch (e: Exception) {
                    Toast.makeText(context, "Email: ${MarketplaceData.SUPPORT_EMAIL}", Toast.LENGTH_SHORT).show()
                  }
                }
              )

              HorizontalDivider(color = Slate100, modifier = Modifier.padding(horizontal = 16.dp))

              // Web Portal & APK Download Row
              ProfileOptionRow(
                icon = Icons.Default.Language,
                iconTint = Slate800,
                title = "Official Website & APK Portal",
                subtitle = "View web version & installation guide",
                onClick = onOpenWebPortal
              )

              HorizontalDivider(color = Slate100, modifier = Modifier.padding(horizontal = 16.dp))

              // Share App Row
              ProfileOptionRow(
                icon = Icons.Default.Share,
                iconTint = BazaarOrangeDark,
                title = "Share App with Friends",
                subtitle = "Invite neighbors to buy & sell locally",
                onClick = {
                  val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                      Intent.EXTRA_TEXT,
                      "Download Meri Local Bazaar Android App!\nBuy, sell, book local cabs, and discover local services in your neighborhood with zero commission:\nhttps://github.com/silgrakmarak1309-hash/Apna-Local-Bazaar-1/releases/tag/v1.0.0"
                    )
                    type = "text/plain"
                  }
                  context.startActivity(Intent.createChooser(sendIntent, "Share Meri Local Bazaar"))
                }
              )
            }
          }
        }
      }

      // 4. App Info & Version
      item {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "Meri Local Bazaar Android App",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Slate600
          )
          Text(
            text = "Version 1.0.0 (Official Build) • 100% Free Marketplace",
            fontSize = 11.sp,
            color = Slate400,
            modifier = Modifier.padding(top = 2.dp)
          )
        }
      }
    }

    if (showEditProfileDialog) {
      EditProfileDialog(
        currentProfile = userProfile,
        onDismiss = { showEditProfileDialog = false },
        onSave = { updated ->
          onProfileUpdated(updated)
          showEditProfileDialog = false
          Toast.makeText(context, "Profile updated!", Toast.LENGTH_SHORT).show()
        }
      )
    }
  }
}

@Composable
fun ProfileHeaderCard(
  userProfile: UserProfileData,
  onEditProfile: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
      .testTag("profile_header_card"),
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = Slate900)
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(
          Brush.horizontalGradient(
            listOf(Slate900, Color(0xFF1E293B), BazaarOrangeDark.copy(alpha = 0.8f))
          )
        )
        .padding(18.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Surface(
            shape = CircleShape,
            color = BazaarOrange,
            modifier = Modifier.size(54.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
              )
            }
          }

          Spacer(modifier = Modifier.width(12.dp))

          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = userProfile.name,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
              Spacer(modifier = Modifier.width(4.dp))
              Icon(
                Icons.Default.Verified,
                contentDescription = null,
                tint = BazaarGold,
                modifier = Modifier.size(16.dp)
              )
            }

            Text(
              text = "+91 ${userProfile.phone}",
              fontSize = 13.sp,
              color = Slate300
            )

            Text(
              text = userProfile.location,
              fontSize = 11.sp,
              color = Slate400
            )
          }
        }

        IconButton(
          onClick = onEditProfile,
          modifier = Modifier
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.15f))
            .size(36.dp)
        ) {
          Icon(Icons.Default.Edit, contentDescription = "Edit Profile", tint = Color.White, modifier = Modifier.size(18.dp))
        }
      }
    }
  }
}

@Composable
fun ProfileOptionRow(
  icon: ImageVector,
  iconTint: Color,
  title: String,
  subtitle: String,
  onClick: () -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onClick() }
      .padding(horizontal = 16.dp, vertical = 14.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.weight(1f)
    ) {
      Surface(
        shape = CircleShape,
        color = iconTint.copy(alpha = 0.12f),
        modifier = Modifier.size(38.dp)
      ) {
        Box(contentAlignment = Alignment.Center) {
          Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(20.dp))
        }
      }

      Spacer(modifier = Modifier.width(12.dp))

      Column {
        Text(title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Slate900)
        Text(subtitle, fontSize = 12.sp, color = Slate500)
      }
    }

    Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Slate400)
  }
}

@Composable
fun EditProfileDialog(
  currentProfile: UserProfileData,
  onDismiss: () -> Unit,
  onSave: (UserProfileData) -> Unit
) {
  var name by remember { mutableStateOf(currentProfile.name) }
  var phone by remember { mutableStateOf(currentProfile.phone) }
  var location by remember { mutableStateOf(currentProfile.location) }

  AlertDialog(
    onDismissRequest = onDismiss,
    title = { Text("Edit Your Profile", fontSize = 17.sp, fontWeight = FontWeight.Bold) },
    text = {
      Column(
        modifier = Modifier.fillMaxWidth().testTag("edit_profile_dialog"),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        OutlinedTextField(
          value = name,
          onValueChange = { name = it },
          label = { Text("Your Name") },
          singleLine = true,
          modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
          value = phone,
          onValueChange = { phone = it },
          label = { Text("WhatsApp Phone Number") },
          singleLine = true,
          modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
          value = location,
          onValueChange = { location = it },
          label = { Text("City / Local Area") },
          singleLine = true,
          modifier = Modifier.fillMaxWidth()
        )
      }
    },
    confirmButton = {
      Button(
        onClick = {
          onSave(
            currentProfile.copy(
              name = name.ifBlank { "User" },
              phone = phone.ifBlank { "6009092096" },
              location = location.ifBlank { "Local Area" }
            )
          )
        },
        colors = ButtonDefaults.buttonColors(containerColor = BazaarOrange)
      ) {
        Text("Save")
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) { Text("Cancel") }
    }
  )
}
