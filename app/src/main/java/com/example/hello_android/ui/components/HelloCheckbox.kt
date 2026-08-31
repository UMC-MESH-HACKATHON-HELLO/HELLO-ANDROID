package com.example.hello_android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.hello_android.R
import com.example.hello_android.ui.theme.helloColors
import com.example.hello_android.ui.theme.helloTypography

@Composable
fun HelloCheckbox(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.helloColors
    val checkboxShape = RoundedCornerShape(4.8.dp)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .border(1.dp, colors.borderSelection, RoundedCornerShape(5.dp))
            .toggleable(
                value = checked,
                role = Role.Checkbox,
                onValueChange = onCheckedChange,
            )
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .then(
                    if (checked) {
                        Modifier.background(colors.actionPrimary, checkboxShape)
                    } else {
                        Modifier
                            .background(colors.surfaceDefault, checkboxShape)
                            .border(1.2.dp, colors.borderSubtle, checkboxShape)
                    },
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (checked) {
                Image(
                    painter = painterResource(R.drawable.ic_report_check),
                    contentDescription = null,
                    modifier = Modifier.size(14.4.dp),
                )
            }
        }
        Text(
            text = text,
            style = MaterialTheme.helloTypography.body3Medium,
            color = colors.textPrimary,
            modifier = Modifier.padding(start = 10.dp),
        )
    }
}
