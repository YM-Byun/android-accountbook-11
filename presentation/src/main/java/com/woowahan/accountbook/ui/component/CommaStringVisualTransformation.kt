package com.woowahan.accountbook.ui.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.core.text.isDigitsOnly

class CommaStringVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val commaString = if (text.text.isDigitsOnly() && text.text.isNotEmpty()) {
            String.format("%,d", text.text.toLong())
        } else {
            text
        }

        val offsetMapping = object : OffsetMapping {
            val initSize = 0

            override fun originalToTransformed(offset: Int): Int {
                val commas = commaString.count { it == ',' }
                return if (offset == 0) initSize else offset + commas
            }

            override fun transformedToOriginal(offset: Int): Int {
                val commas = commaString.count { it == ',' }
                return offset + commas
            }
        }

        return TransformedText(
            text = AnnotatedString("$commaString"),
            offsetMapping = offsetMapping
        )
    }
}