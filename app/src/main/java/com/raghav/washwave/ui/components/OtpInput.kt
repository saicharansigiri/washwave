
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OTPInput(
    title: String = "Verification code",
    codeLength: Int = 6,
    onValueChange: (String) -> Unit = {},
    onCodeComplete: (String) -> Unit
) {
    var code by remember { mutableStateOf(List(codeLength) { "" }) }
    val focusRequesters = remember { List(codeLength) { FocusRequester() } }
    val keyboardController = LocalSoftwareKeyboardController.current // Use keyboard controller

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween // Space the fields equally
        ) {
            code.forEachIndexed { index, digit ->
                OutlinedTextField(
                    value = digit,
                    colors = TextFieldDefaults.colors().copy(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = { newValue ->
                        if (newValue.length <= 1) {
                            code = code.toMutableList().also { it[index] = newValue }
                            if (newValue.isNotEmpty() && index < codeLength - 1) {
                                focusRequesters[index + 1].requestFocus()
                            }
                        }

                        val otpString = code.joinToString(separator = "")
                        onValueChange.invoke(otpString)
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 6.dp) // Add horizontal padding for spacing
                        .focusRequester(focusRequesters[index])
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Backspace && digit.isEmpty() && index > 0) {
                                code = code.toMutableList().also { it[index - 1] = "" }
                                focusRequesters[index - 1].requestFocus()
                                true
                            } else {
                                false
                            }
                        },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            if (code.all { it.isNotEmpty() }) {
                                onCodeComplete(code.joinToString(""))
                            }
                        }
                    ),
                    singleLine = true
                )
            }
        }
    }
}