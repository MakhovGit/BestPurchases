package org.purchases.best.ui.screens._elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.purchases.best.R
import org.purchases.best.ui.theme.ButtonIconBackgroundColor
import org.purchases.best.ui.theme.ButtonIconColor
import org.purchases.best.ui.theme.ButtonTextColor
import org.purchases.best.ui.theme.PrimaryButtonColor

@Composable
fun IconButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner_shape_button)),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryButtonColor,
            contentColor = ButtonTextColor
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = dimensionResource(id = R.dimen.button_elevation),
            pressedElevation = dimensionResource(id = R.dimen.button_pressed_elevation)
        )
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(vertical = dimensionResource(id = R.dimen.button_vertical_padding))
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_add_24),
                contentDescription = stringResource(id = R.string.list_screen_icon_content_description),
                tint = ButtonIconColor,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.button_icon_size))
                    .background(
                        color = ButtonIconBackgroundColor,
                        shape = CircleShape
                    )
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacer_10)))
            Text(
                text = text,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = dimensionResource(id = R.dimen.button_font_size).value.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}