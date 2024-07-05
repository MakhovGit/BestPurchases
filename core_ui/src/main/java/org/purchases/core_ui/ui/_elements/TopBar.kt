package org.purchases.core_ui.ui._elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.purchases.core_ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(listName: String, navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = listName,
                textAlign = TextAlign.Start,
                fontSize = dimensionResource(id = R.dimen.main_title_font_size).value.sp,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.elements_top_bar_icon_content_description)
                )
            }
        }
    )
}
