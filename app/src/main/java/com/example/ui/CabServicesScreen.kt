package com.example.ui

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
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TwoWheeler
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CabDriverListing
import com.example.data.MarketplaceData
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
fun CabServicesScreen(
  drivers: List<CabDriverListing>,
  onRegisterDriverClick: (CabDriverListing) -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var searchQuery by remember { mutableStateOf("") }
  var showRegisterDialog by remember { mutableStateOf(false) }

  val filteredDrivers = remember(drivers, searchQuery) {
    if (searchQuery.isBlank()) drivers
    else {
      drivers.filter {
        it.driverName.contains(searchQuery, ignoreCase = true) ||
            it.vehicleType.contains(searchQuery, ignoreCase = true) ||
            it.routesCovered.contains(searchQuery, ignoreCase = true) ||
            it.baseLocation.contains(searchQuery, ignoreCase = true)
      }
    }
  }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    LazyColumn(
      modifier = Modifier.fillMaxSize().testTag("cab_services_list"),
      contentPadding = PaddingValues(bottom = 90.dp)
    ) {
      // Header Banner
      item {
        CabHeroBanner(
          onRegisterDriver = { showRegisterDialog = true }
        )
      }

      // Search & Filters
      item {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
          OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search cab, auto, bike taxi, airport drop, route...", fontSize = 13.sp) },
            leadingIcon = {
              Icon(Icons.Default.Search, contentDescription = null, tint = BazaarTeal)
            },
            modifier = Modifier
              .fillMaxWidth()
              .testTag("cab_search_input"),
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = BazaarTeal,
              unfocusedBorderColor = Slate300,
              focusedContainerColor = MaterialTheme.colorScheme.surface,
              unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
          )
        }
      }

      // Title
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "Available Drivers & Cabs",
              fontSize = 17.sp,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              text = "${filteredDrivers.size} active local drivers • Zero platform commission",
              fontSize = 12.sp,
              color = Slate500
            )
          }

          Surface(
            shape = RoundedCornerShape(8.dp),
            color = SuccessGreen.copy(alpha = 0.15f)
          ) {
            Text(
              text = "Direct Booking",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = SuccessGreen,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }
        }
      }

      // Driver Cards
      items(filteredDrivers, key = { it.id }) { driver ->
        CabDriverCard(
          driver = driver,
          onWhatsAppClick = {
            openWhatsAppChat(
              context = context,
              phone = driver.phone,
              message = "Hello ${driver.driverName}, I found your cab (${driver.vehicleType}) on Meri Local Bazaar. Are you available for a ride?"
            )
          },
          onCallClick = {
            openPhoneDialer(context, driver.phone)
          }
        )
      }
    }

    if (showRegisterDialog) {
      RegisterDriverDialog(
        onDismiss = { showRegisterDialog = false },
        onSubmit = { newDriver ->
          onRegisterDriverClick(newDriver)
          showRegisterDialog = false
          Toast.makeText(context, "Driver profile added successfully!", Toast.LENGTH_SHORT).show()
        }
      )
    }
  }
}

@Composable
fun CabHeroBanner(onRegisterDriver: () -> Unit) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
      .testTag("cab_hero_banner"),
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = Slate900),
    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(
          Brush.horizontalGradient(
            listOf(Color(0xFF004D40), Color(0xFF00695C), Color(0xFF0F172A))
          )
        )
        .padding(16.dp)
    ) {
      Column(modifier = Modifier.fillMaxWidth()) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = BazaarTealContainer
          ) {
            Text(
              text = "LOCAL CAB & RIDES",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = BazaarTealDark,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
            )
          }

          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Verified,
              contentDescription = null,
              tint = BazaarGold,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("0% Surge Price", fontSize = 11.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
          text = "Local Taxi, Auto & Outstation Cabs",
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
        Text(
          text = "Call or chat on WhatsApp directly with trusted local drivers in Garo Hills & Meghalaya.",
          fontSize = 12.sp,
          color = Slate200,
          modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Button(
            onClick = onRegisterDriver,
            colors = ButtonDefaults.buttonColors(containerColor = BazaarOrange),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f).height(38.dp)
          ) {
            Text("+ Register As Driver", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
          }

          Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.White.copy(alpha = 0.15f),
            modifier = Modifier.height(38.dp)
          ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 10.dp)) {
              Text("Verified Drivers", fontSize = 11.sp, color = Color.White, fontWeight = FontWeight.Medium)
            }
          }
        }
      }
    }
  }
}

@Composable
fun CabDriverCard(
  driver: CabDriverListing,
  onWhatsAppClick: () -> Unit,
  onCallClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 6.dp)
      .testTag("driver_card_${driver.id}"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = BorderStroke(1.dp, Slate200),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(modifier = Modifier.fillMaxWidth().padding(14.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Surface(
            shape = CircleShape,
            color = BazaarTealContainer,
            modifier = Modifier.size(42.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = if (driver.vehicleType.contains("Bike", true)) Icons.Default.TwoWheeler else Icons.Default.DirectionsCar,
                contentDescription = null,
                tint = BazaarTealDark,
                modifier = Modifier.size(22.dp)
              )
            }
          }

          Spacer(modifier = Modifier.width(10.dp))

          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = driver.driverName,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
              )
              Spacer(modifier = Modifier.width(4.dp))
              Icon(
                imageVector = Icons.Default.Verified,
                contentDescription = null,
                tint = BazaarTeal,
                modifier = Modifier.size(14.dp)
              )
            }
            Text(
              text = "${driver.vehicleType} • ${driver.vehicleNumber}",
              fontSize = 12.sp,
              color = Slate500
            )
          }
        }

        Surface(
          shape = RoundedCornerShape(6.dp),
          color = if (driver.isAvailableNow) SuccessGreen.copy(alpha = 0.15f) else Slate200
        ) {
          Text(
            text = if (driver.isAvailableNow) "● Available Now" else "Offline",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = if (driver.isAvailableNow) SuccessGreen else Slate600,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Route Covered
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
      ) {
        Icon(
          imageVector = Icons.Default.Route,
          contentDescription = null,
          tint = BazaarOrange,
          modifier = Modifier.size(16.dp).padding(top = 1.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Routes: ${driver.routesCovered}",
          fontSize = 12.sp,
          color = Slate700,
          lineHeight = 16.sp
        )
      }

      Spacer(modifier = Modifier.height(6.dp))

      // Stand Location & Fare
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = null,
            tint = Slate400,
            modifier = Modifier.size(14.dp)
          )
          Spacer(modifier = Modifier.width(2.dp))
          Text(
            text = driver.baseLocation,
            fontSize = 11.sp,
            color = Slate500
          )
        }

        Text(
          text = driver.fareEstimate,
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = BazaarTealDark
        )
      }

      HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = Slate100)

      // Actions (WhatsApp & Call)
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Button(
          onClick = onWhatsAppClick,
          modifier = Modifier
            .weight(1f)
            .height(38.dp)
            .testTag("driver_whatsapp_${driver.id}"),
          colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
          shape = RoundedCornerShape(10.dp),
          contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
          Icon(Icons.Outlined.Chat, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text("WhatsApp Driver", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        OutlinedButton(
          onClick = onCallClick,
          modifier = Modifier
            .height(38.dp)
            .testTag("driver_call_${driver.id}"),
          shape = RoundedCornerShape(10.dp),
          colors = ButtonDefaults.outlinedButtonColors(contentColor = Slate800),
          border = BorderStroke(1.dp, Slate300),
          contentPadding = PaddingValues(horizontal = 14.dp)
        ) {
          Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text("Call", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        }
      }
    }
  }
}

@Composable
fun RegisterDriverDialog(
  onDismiss: () -> Unit,
  onSubmit: (CabDriverListing) -> Unit
) {
  var driverName by remember { mutableStateOf("") }
  var vehicleType by remember { mutableStateOf("Taxi / Cab") }
  var vehicleNumber by remember { mutableStateOf("") }
  var baseLocation by remember { mutableStateOf("Tura") }
  var routesCovered by remember { mutableStateOf("") }
  var fareEstimate by remember { mutableStateOf("Standard / Negotiable") }
  var phone by remember { mutableStateOf("6009092096") }

  AlertDialog(
    onDismissRequest = onDismiss,
    title = {
      Text("Register as Driver / Cab", fontSize = 17.sp, fontWeight = FontWeight.Bold)
    },
    text = {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("register_driver_dialog"),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        OutlinedTextField(
          value = driverName,
          onValueChange = { driverName = it },
          label = { Text("Your Full Name") },
          modifier = Modifier.fillMaxWidth(),
          singleLine = true
        )
        OutlinedTextField(
          value = vehicleType,
          onValueChange = { vehicleType = it },
          label = { Text("Vehicle Type (Auto / Swift / Bike Taxi / Pickup)") },
          modifier = Modifier.fillMaxWidth(),
          singleLine = true
        )
        OutlinedTextField(
          value = vehicleNumber,
          onValueChange = { vehicleNumber = it },
          label = { Text("Vehicle Number (e.g. ML 08 X 1234)") },
          modifier = Modifier.fillMaxWidth(),
          singleLine = true
        )
        OutlinedTextField(
          value = routesCovered,
          onValueChange = { routesCovered = it },
          label = { Text("Routes Covered (e.g. Tura, Guwahati, Local)") },
          modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
          value = phone,
          onValueChange = { phone = it },
          label = { Text("WhatsApp & Call Phone Number") },
          modifier = Modifier.fillMaxWidth(),
          singleLine = true
        )
      }
    },
    confirmButton = {
      Button(
        onClick = {
          if (driverName.isNotBlank() && phone.isNotBlank()) {
            onSubmit(
              CabDriverListing(
                id = "driver_${System.currentTimeMillis()}",
                driverName = driverName,
                vehicleType = vehicleType,
                vehicleNumber = vehicleNumber.ifBlank { "Registered" },
                baseLocation = baseLocation,
                routesCovered = routesCovered.ifBlank { "Local and nearby areas" },
                fareEstimate = fareEstimate,
                phone = phone,
                isAvailableNow = true
              )
            )
          }
        },
        colors = ButtonDefaults.buttonColors(containerColor = BazaarOrange)
      ) {
        Text("Submit & Go Live")
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) {
        Text("Cancel")
      }
    }
  )
}
