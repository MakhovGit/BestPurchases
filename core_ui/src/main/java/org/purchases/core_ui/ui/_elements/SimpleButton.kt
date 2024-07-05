package org.purchases.core_ui.ui._elements

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.purchases.core_ui.R
import org.purchases.core_ui.ui.theme.ButtonTextColor
import org.purchases.core_ui.ui.theme.PrimaryButtonColor
import org.purchases.core_ui.ui.theme.SecondaryButtonColor

@Composable
fun SimpleButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isCancelButton: Boolean = false
) {
    Button(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner_shape_button)),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isCancelButton) SecondaryButtonColor else PrimaryButtonColor,
            contentColor = ButtonTextColor
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = dimensionResource(id = R.dimen.button_elevation),
            pressedElevation = dimensionResource(id = R.dimen.button_pressed_elevation)
        ),
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = dimensionResource(id = R.dimen.button_font_size).value.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(vertical = dimensionResource(id = R.dimen.button_vertical_padding))
        )
    }
}