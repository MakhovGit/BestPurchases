package org.purchases.home_screen.ui._elements

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import org.purchases.core.model.info.ListInfo
import org.purchases.core_ui.utils.floatResource
import org.purchases.home_screen.R
import org.purchases.core_ui.R as core_ui_R

@SuppressLint("RestrictedApi")
@Composable
fun HomeScreenCard(
    list: ListInfo,
    onCardClick: () -> Unit,
    onDeleteListMenuItemClick: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        shape = RoundedCornerShape(dimensionResource(id = core_ui_R.dimen.rounded_corner_shape_card)),
        color = Color.White,
        shadowElevation = dimensionResource(id = core_ui_R.dimen.card_shadow_elevation),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onCardClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimensionResource(id = R.dimen.home_screen_card_start_padding))
                .padding(vertical = dimensionResource(id = R.dimen.home_screen_card_vertical_padding))
        ) {
            Text(
                text = list.title,
                textAlign = TextAlign.Start,
                fontSize = dimensionResource(id = R.dimen.home_screen_card_font_size).value.sp,
                maxLines = integerResource(id = R.integer.home_screen_card_text_max_lines),
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(floatResource(id = core_ui_R.dimen.max_weight))
            )
            Box {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_more_vert_24),
                    contentDescription = stringResource(id = R.string.card_icon_content_description),
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.home_screen_card_icon_size))
                        .clickable { expanded = true }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(id = R.string.menu_item_delete)) },
                        onClick = {
                            onDeleteListMenuItemClick()
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
